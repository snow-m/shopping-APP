package com.taobao;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.huohoubrowser.zxing.BarcodeFormat;
import com.huohoubrowser.zxing.BinaryBitmap;
import com.huohoubrowser.zxing.BitmapLuminanceSource;
import com.huohoubrowser.zxing.DecodeHintType;
import com.huohoubrowser.zxing.MultiFormatReader;
import com.huohoubrowser.zxing.ReaderException;
import com.huohoubrowser.zxing.Result;
import com.huohoubrowser.zxing.ResultMetadataType;
import com.huohoubrowser.zxing.ResultPoint;
import com.huohoubrowser.zxing.client.android.BeepManager;
import com.huohoubrowser.zxing.client.android.CaptureActivityHandler;
import com.huohoubrowser.zxing.client.android.DecodeFormatManager;
import com.huohoubrowser.zxing.client.android.InactivityTimer;
import com.huohoubrowser.zxing.client.android.IntentSource;
import com.huohoubrowser.zxing.client.android.Intents;
import com.huohoubrowser.zxing.client.android.ViewfinderView;
import com.huohoubrowser.zxing.client.android.camera.CameraManager;
import com.huohoubrowser.zxing.client.android.result.ResultButtonListener;
import com.huohoubrowser.zxing.client.android.result.ResultHandler;
import com.huohoubrowser.zxing.client.android.result.ResultHandlerFactory;
import com.huohoubrowser.zxing.common.HybridBinarizer;









import com.taobao.utils.ApplicationUtils;
import com.taobao.utils.Constants;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CaptureActivity extends BaseActivity implements SurfaceHolder.Callback {
	private static final String TAG = CaptureActivity.class.getSimpleName();

	private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 1500L;
	private static final long BULK_MODE_SCAN_DELAY_MS = 1000L;

	private static final String PRODUCT_SEARCH_URL_PREFIX = "http://www.google";
	private static final String PRODUCT_SEARCH_URL_SUFFIX = "/m/products/scan";
	private static final String RETURN_CODE_PLACEHOLDER = "{CODE}";

	private static final String RETURN_URL_PARAM = "ret";
	private static final String RAW_PARAM = "raw";
	private static final Set<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE,
			ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);

	public static final int OPEN_CODE_AUTO_RETURN = 0;
	public static final int OPEN_CODE_PIC = 1;
	CameraManager cameraManager;
	private CaptureActivityHandler handler;
	private Result savedResultToShow;
	private ViewfinderView viewfinderView;
	private TextView statusView;
	private boolean hasSurface;
	private Result lastResult;
	private InactivityTimer inactivityTimer;
	private BeepManager beepManager;
	private View resultView;
	private IntentSource source;
	private boolean copyToClipboard;
	private String returnUrlTemplate;
	private boolean returnRaw;
	private String sourceUrl;
	private String characterSet;
	private int mOpenCode = -1;
	private Collection<BarcodeFormat> decodeFormats;
	private String mComeUrl = null;
	private Button mBackBtn = null;
	private ImageView mFlashlight;
	private boolean mIsFlashLightOpen = false;
	private ImageView mClose;
	private Handler mHandler = new Handler();
	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.capture_activity);
		mBackBtn = (Button) findViewById(R.id.back_btn);
		mFlashlight = (ImageView) findViewById(R.id.flashlight);
		
		mClose = (ImageView) findViewById(R.id.close);
		mClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				restartPreviewAfterDelay(0L);
				mBackBtn.setVisibility(View.VISIBLE);
				mFlashlight.setVisibility(View.VISIBLE);
				mClose.setVisibility(View.GONE);
			}
		});
		mFlashlight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mFlashlight.isSelected()){
					mFlashlight.setSelected(false);
					mIsFlashLightOpen = false;
				}else{
					mFlashlight.setSelected(true);
					mIsFlashLightOpen = true;
				}
				cameraManager.setTorch(mIsFlashLightOpen);
			}
		});
		mBackBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		beepManager = new BeepManager(this);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		Intent gIntent = getIntent();
		if (gIntent != null) {
			mOpenCode = gIntent.getIntExtra(Constants.EXTRA_ID_OPEN, -1);
			if (mOpenCode == OPEN_CODE_PIC) {
				mComeUrl = gIntent.getStringExtra(Constants.EXTRA_ID_URL);
			}
		}
	}

	/**
	 * A valid barcode has been found, so give an indication of success and show
	 * the results.
	 * 
	 * @param rawResult
	 *            The contents of the barcode.
	 * @param barcode
	 *            A greyscale bitmap of the camera data which was decoded.
	 */
	public void handleDecode(final Result rawResult, final Bitmap barcode) {
		inactivityTimer.onActivity();
		lastResult = rawResult;
		final ResultHandler resultHandler = ResultHandlerFactory.makeResultHandler(this, rawResult);

		if (rawResult!=null){
			Log.i(TAG, "rawResult:"+rawResult.getText());
		}
		
		boolean fromLiveScan = barcode != null;
		if (fromLiveScan) {
			// historyManager.addHistoryItem(rawResult, resultHandler);
			// Then not from history, so beep/vibrate and we have an image to
			// draw on
			beepManager.playBeepSoundAndVibrate();
			try {
				drawResultPoints(barcode, rawResult);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		switch (source) {
		case NATIVE_APP_INTENT:
		case PRODUCT_SEARCH_LINK:
			handleDecodeExternally(rawResult, resultHandler, barcode);
			break;
		// case ZXING_LINK:
		// if (returnUrlTemplate == null) {
		// handleDecodeInternally(rawResult, resultHandler, barcode);
		// } else {
		// handleDecodeExternally(rawResult, resultHandler, barcode);
		// }
		// break;
		case NONE:
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			if (fromLiveScan && prefs.getBoolean(CapturePreferenceActivity.KEY_BULK_MODE, false)) {
				String message = getResources().getString(R.string.msg_bulk_mode_scanned) + " (" + rawResult.getText() + ')';
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
				// Wait a moment or else it will scan the same barcode
				// continuously about 3 times
				restartPreviewAfterDelay(BULK_MODE_SCAN_DELAY_MS);
			} else {
				mHandler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						handleDecodeInternally(rawResult, resultHandler, barcode);
					}
				});
			
			}
			break;
		}
	}

	public void restartPreviewAfterDelay(long delayMS) {
		if (handler != null) {
			handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
		}
		resetStatusView();
	}

	// Put up our own UI for how to handle the decoded contents.
	private void handleDecodeInternally(Result rawResult, ResultHandler resultHandler, Bitmap barcode) {
		mBackBtn.setVisibility(View.GONE);
		mFlashlight.setVisibility(View.GONE);
		cameraManager.setTorch(false);
		mClose.setVisibility(View.VISIBLE);
		statusView.setVisibility(View.GONE);
		viewfinderView.setVisibility(View.GONE);
		resultView.setVisibility(View.VISIBLE);
		Log.i(TAG, "handleDecodeInternally");
		ImageView barcodeImageView = (ImageView) findViewById(R.id.barcode_image_view);
		if (barcode == null) {
			barcodeImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bcode));
		} else {
			barcodeImageView.setImageBitmap(barcode);
		}

		TextView formatTextView = (TextView) findViewById(R.id.format_text_view);
		formatTextView.setText(rawResult.getBarcodeFormat().toString());

		TextView typeTextView = (TextView) findViewById(R.id.type_text_view);
		typeTextView.setText(resultHandler.getType().toString());

		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		String formattedTime = formatter.format(new Date(rawResult.getTimestamp()));
		TextView timeTextView = (TextView) findViewById(R.id.time_text_view);
		timeTextView.setText(formattedTime);

		TextView metaTextView = (TextView) findViewById(R.id.meta_text_view);
		View metaTextViewLabel = findViewById(R.id.meta_text_view_label);
		metaTextView.setVisibility(View.GONE);
		metaTextViewLabel.setVisibility(View.GONE);
		Map<ResultMetadataType, Object> metadata = rawResult.getResultMetadata();
		if (metadata != null) {
			StringBuilder metadataText = new StringBuilder(20);
			for (Map.Entry<ResultMetadataType, Object> entry : metadata.entrySet()) {
				if (DISPLAYABLE_METADATA_TYPES.contains(entry.getKey())) {
					metadataText.append(entry.getValue()).append('\n');
				}
			}
			if (metadataText.length() > 0) {
				metadataText.setLength(metadataText.length() - 1);
				metaTextView.setText(metadataText);
				metaTextView.setVisibility(View.VISIBLE);
				metaTextViewLabel.setVisibility(View.VISIBLE);
			}
		}

		TextView contentsTextView = (TextView) findViewById(R.id.contents_text_view);
		final CharSequence displayContents = resultHandler.getDisplayContents();
		contentsTextView.setText(displayContents);
		Log.e("displayContents",displayContents+"");
		// Crudely scale betweeen 22 and 32 -- bigger font for shorter text
		int scaledSize = Math.max(22, 32 - displayContents.length() / 4);
		contentsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaledSize);

		TextView supplementTextView = (TextView) findViewById(R.id.contents_supplement_text_view);
		supplementTextView.setText("");
		supplementTextView.setOnClickListener(null);
		if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(CapturePreferenceActivity.KEY_SUPPLEMENTAL, true)) {
			// SupplementalInfoRetriever.maybeInvokeRetrieval(supplementTextView,
			// resultHandler.getResult(),
			// historyManager,
			// this);
		}

//		int buttonCount = resultHandler.getButtonCount();
		if(!TextUtils.isEmpty(displayContents)){
			ViewGroup buttonView = (ViewGroup) findViewById(R.id.result_button_view);
			buttonView.requestFocus();
			TextView button = (TextView) buttonView.getChildAt(0);
			button.setText("搜索产品");
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.putExtra("displayContents", displayContents);
					setResult(RESULT_OK, intent);
					finish();
				}
			});
			button.setVisibility(View.VISIBLE);
		}
		
//		for (int x = 0; x < ResultHandler.MAX_BUTTON_COUNT; x++) {
//			TextView button = (TextView) buttonView.getChildAt(x);
//			if (x < buttonCount) {
//				button.setVisibility(View.VISIBLE);
//				button.setText(resultHandler.getButtonText(x));
//				button.setOnClickListener(new ResultButtonListener(resultHandler, x));
//			} else {
//				button.setVisibility(View.GONE);
//			}
//		}
		
		if (copyToClipboard && !resultHandler.areContentsSecure()) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			if (displayContents != null) {
				clipboard.setText(displayContents.toString());
				// Toast.makeText(CaptureActivity.this,
				// R.string.Commons_CopyToastMessage,
				// Toast.LENGTH_SHORT).show();
			}
		}
		if (mOpenCode == OPEN_CODE_AUTO_RETURN && displayContents != null) {
			// Log.i(TAG, displayContents.toString());
			if (displayContents.toString().startsWith("http://")) {
				Intent rIntent = new Intent();
				rIntent.putExtra("CAPTURE_URL", displayContents.toString());
				// Log.i(TAG, "CAPTURE:"+displayContents);
				setResult(RESULT_OK, rIntent);
				finish();
			}
		}
	}

	// Briefly show the contents of the barcode, then handle the result outside
	// Barcode Scanner.
	private void handleDecodeExternally(Result rawResult, ResultHandler resultHandler, Bitmap barcode) {
		
		if (barcode != null) {
			mBackBtn.setVisibility(View.GONE);
			mFlashlight.setVisibility(View.GONE);
			cameraManager.setTorch(false);
			mClose.setVisibility(View.VISIBLE);
			viewfinderView.drawResultBitmap(barcode);
		}

		long resultDurationMS;
		if (getIntent() == null) {
			resultDurationMS = DEFAULT_INTENT_RESULT_DURATION_MS;
		} else {
			resultDurationMS = getIntent().getLongExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, DEFAULT_INTENT_RESULT_DURATION_MS);
		}

		// Since this message will only be shown for a second, just tell the
		// user what kind of
		// barcode was found (e.g. contact info) rather than the full contents,
		// which they won't
		// have time to read.
		if (resultDurationMS > 0) {
			statusView.setText(getString(resultHandler.getDisplayTitle()));
		}

		if (copyToClipboard && !resultHandler.areContentsSecure()) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			CharSequence text = resultHandler.getDisplayContents();
			if (text != null) {
				clipboard.setText(text.toString());
			}
		}

		if (source == IntentSource.NATIVE_APP_INTENT) {

			// Hand back whatever action they requested - this can be changed to
			// Intents.Scan.ACTION when
			// the deprecated intent is retired.
			Intent intent = new Intent(getIntent().getAction());
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			intent.putExtra(Intents.Scan.RESULT, rawResult.toString());
			intent.putExtra(Intents.Scan.RESULT_FORMAT, rawResult.getBarcodeFormat().toString());
			byte[] rawBytes = rawResult.getRawBytes();
			if (rawBytes != null && rawBytes.length > 0) {
				intent.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
			}
			Map<ResultMetadataType, ?> metadata = rawResult.getResultMetadata();
			if (metadata != null) {
				if (metadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
					intent.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION, metadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
				}
				Integer orientation = (Integer) metadata.get(ResultMetadataType.ORIENTATION);
				if (orientation != null) {
					intent.putExtra(Intents.Scan.RESULT_ORIENTATION, orientation.intValue());
				}
				String ecLevel = (String) metadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
				if (ecLevel != null) {
					intent.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, ecLevel);
				}
				Iterable<byte[]> byteSegments = (Iterable<byte[]>) metadata.get(ResultMetadataType.BYTE_SEGMENTS);
				if (byteSegments != null) {
					int i = 0;
					for (byte[] byteSegment : byteSegments) {
						intent.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, byteSegment);
						i++;
					}
				}
			}
			sendReplyMessage(R.id.return_scan_result, intent, resultDurationMS);

		} else if (source == IntentSource.PRODUCT_SEARCH_LINK) {

			// Reformulate the URL which triggered us into a query, so that the
			// request goes to the same
			// TLD as the scan URL.
			int end = sourceUrl.lastIndexOf("/scan");
			String replyURL = sourceUrl.substring(0, end) + "?q=" + resultHandler.getDisplayContents() + "&source=zxing";
			sendReplyMessage(R.id.launch_product_query, replyURL, resultDurationMS);

		}
		/*
		 * else if (source == IntentSource.ZXING_LINK) {
		 * 
		 * // Replace each occurrence of RETURN_CODE_PLACEHOLDER in the //
		 * returnUrlTemplate // with the scanned code. This allows both queries
		 * and REST-style // URLs to work. if (returnUrlTemplate != null) {
		 * CharSequence codeReplacement = returnRaw ? rawResult.getText() :
		 * resultHandler.getDisplayContents(); try { codeReplacement =
		 * URLEncoder.encode( codeReplacement.toString(), "UTF-8"); } catch
		 * (UnsupportedEncodingException e) { // can't happen; UTF-8 is always
		 * supported. Continue, I // guess, without encoding } String replyURL =
		 * returnUrlTemplate.replace( RETURN_CODE_PLACEHOLDER, codeReplacement);
		 * sendReplyMessage(R.id.launch_product_query, replyURL,
		 * resultDurationMS); } }
		 */
	}

	private void sendReplyMessage(int id, Object arg, long delayMS) {
		Message message = Message.obtain(handler, id, arg);
		if (delayMS > 0L) {
			handler.sendMessageDelayed(message, delayMS);
		} else {
			handler.sendMessage(message);
		}
	}

	/**
	 * Superimpose a line for 1D or dots for 2D to highlight the key features of
	 * the barcode.
	 * 
	 * @param barcode
	 *            A bitmap of the captured image.
	 * @param rawResult
	 *            The decoded results which contains the points to draw.
	 */
	private void drawResultPoints(Bitmap barcode, Result rawResult) {
		ResultPoint[] points = rawResult.getResultPoints();
		if (points != null && points.length > 0) {
			Canvas canvas = new Canvas(barcode);
			Paint paint = new Paint();
			paint.setColor(getResources().getColor(R.color.result_points));
			if (points.length == 2) {
				paint.setStrokeWidth(4.0f);
				drawLine(canvas, paint, points[0], points[1]);
			} else if (points.length == 4 && (rawResult.getBarcodeFormat() == BarcodeFormat.UPC_A || rawResult.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
				// Hacky special case -- draw two lines, for the barcode and
				// metadata
				drawLine(canvas, paint, points[0], points[1]);
				drawLine(canvas, paint, points[2], points[3]);
			} else {
				paint.setStrokeWidth(10.0f);
				for (ResultPoint point : points) {
					canvas.drawPoint(point.getX(), point.getY(), paint);
				}
			}
		}
	}

	private static void drawLine(Canvas canvas, Paint paint, ResultPoint a, ResultPoint b) {
		canvas.drawLine(a.getX(), a.getY(), b.getX(), b.getY(), paint);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// CameraManager must be initialized here, not in onCreate(). This is
		// necessary because we don't
		// want to open the camera driver and measure the screen size if we're
		// going to show the help on
		// first launch. That led to bugs where the scanning rectangle was the
		// wrong size and partially
		// off screen.
		cameraManager = new CameraManager(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		viewfinderView.setCameraManager(cameraManager);
		
		resultView = findViewById(R.id.result_view);
		statusView = (TextView) findViewById(R.id.status_view);

		handler = null;
		lastResult = null;

		resetStatusView();

		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			// The activity was paused but not stopped, so the surface still
			// exists. Therefore
			// surfaceCreated() won't be called, so init the camera here.
			initCamera(surfaceHolder);
		} else {
			// Install the callback and wait for surfaceCreated() to init the
			// camera.
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}

		beepManager.updatePrefs();

		inactivityTimer.onResume();

		Intent intent = getIntent();

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		copyToClipboard = prefs.getBoolean(CapturePreferenceActivity.KEY_COPY_TO_CLIPBOARD, true)
				&& (intent == null || intent.getBooleanExtra(Intents.Scan.SAVE_HISTORY, true));

		source = IntentSource.NONE;
		decodeFormats = null;
		characterSet = null;
		if (mOpenCode == OPEN_CODE_PIC && !TextUtils.isEmpty(mComeUrl)) {
			Log.i(TAG, "mComeUrl:" + mComeUrl);
			ExecutorService e = Executors.newCachedThreadPool();
			e.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					final Bitmap bitmap = ApplicationUtils.getUrlBitmap(mComeUrl);
					if(bitmap!=null){
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								decodeComeBitmap(bitmap);
							}
						});
					}
					
				}
			});
			return;
		}
		if (intent != null) {

			String action = intent.getAction();
			String dataString = intent.getDataString();

			if (Intents.Scan.ACTION.equals(action)) {

				// Scan the formats the intent requested, and return the result
				// to the calling activity.
				source = IntentSource.NATIVE_APP_INTENT;
				decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);

				if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
					int width = intent.getIntExtra(Intents.Scan.WIDTH, 0);
					int height = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
					if (width > 0 && height > 0) {
						cameraManager.setManualFramingRect(width, height);
					}
				}

				String customPromptMessage = intent.getStringExtra(Intents.Scan.PROMPT_MESSAGE);
				if (customPromptMessage != null) {
					statusView.setText(customPromptMessage);
				}

			} else if (dataString != null && dataString.contains(PRODUCT_SEARCH_URL_PREFIX) && dataString.contains(PRODUCT_SEARCH_URL_SUFFIX)) {

				// Scan only products and send the result to mobile Product
				// Search.
				source = IntentSource.PRODUCT_SEARCH_LINK;
				sourceUrl = dataString;
				decodeFormats = DecodeFormatManager.PRODUCT_FORMATS;

			}
			/*
			 * else if (isZXingURL(dataString)) {
			 * 
			 * // Scan formats requested in query string (all formats if none //
			 * specified). // If a return URL is specified, send the results
			 * there. // Otherwise, handle it ourselves. source =
			 * IntentSource.ZXING_LINK; sourceUrl = dataString; Uri inputUri =
			 * Uri.parse(sourceUrl); returnUrlTemplate = inputUri
			 * .getQueryParameter(RETURN_URL_PARAM); returnRaw =
			 * inputUri.getQueryParameter(RAW_PARAM) != null; decodeFormats =
			 * DecodeFormatManager .parseDecodeFormats(inputUri);
			 * 
			 * }
			 */

			characterSet = intent.getStringExtra(Intents.Scan.CHARACTER_SET);
		}
	
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		if (surfaceHolder == null) {
			throw new IllegalStateException("No SurfaceHolder provided");
		}
		if (cameraManager.isOpen()) {
			Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
			return;
		}
		try {
			cameraManager.openDriver(surfaceHolder);
			// Creating the handler starts the preview, which can also throw a
			// RuntimeException.			
			if (handler == null) {
				handler = new CaptureActivityHandler(this, decodeFormats, characterSet, cameraManager);
			}
			decodeOrStoreSavedBitmap(null, null);
			
		} catch (IOException ioe) {
			Log.w(TAG, ioe);
			// displayFrameworkBugMessageAndExit();
		} catch (RuntimeException e) {
			// Barcode Scanner has seen crashes in the wild of this variety:
			// java.?lang.?RuntimeException: Fail to connect to camera service
			Log.w(TAG, "Unexpected error initializing camera", e);
			// displayFrameworkBugMessageAndExit();
		}
	}

	private void decodeComeBitmap(Bitmap bitmap) {
		if (bitmap != null) {
			Map<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
			Collection<BarcodeFormat> mydecodeFormats;
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			mydecodeFormats = EnumSet.noneOf(BarcodeFormat.class);
			if (prefs.getBoolean(CapturePreferenceActivity.KEY_DECODE_1D, false)) {
				mydecodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
			}
			if (prefs.getBoolean(CapturePreferenceActivity.KEY_DECODE_QR, false)) {
				mydecodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
			}
			if (prefs.getBoolean(CapturePreferenceActivity.KEY_DECODE_DATA_MATRIX, false)) {
				mydecodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
			}
			hints.put(DecodeHintType.POSSIBLE_FORMATS, mydecodeFormats);
			if (characterSet != null) {
				hints.put(DecodeHintType.CHARACTER_SET, characterSet);
			}
			BitmapLuminanceSource source = new BitmapLuminanceSource(bitmap);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
			MultiFormatReader multiFormatReader = new MultiFormatReader();
			multiFormatReader.setHints(hints);
			try {
				Result rawResult = multiFormatReader.decodeWithState(binaryBitmap);		
			    if (rawResult != null) {
			       handleDecode(rawResult,bitmap);
			    } else {
			       Toast.makeText(this, R.string.result_error, Toast.LENGTH_SHORT).show();
			    }
			} catch (ReaderException re) {
				// continue 
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				multiFormatReader.reset();
			}
		}
	}

	private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
		// Bitmap isn't used yet -- will be used soon
		if (handler == null) {
			savedResultToShow = result;
		} else {
			if (result != null) {
				savedResultToShow = result;
			}
			if (savedResultToShow != null) {
				Message message = Message.obtain(handler, R.id.decode_succeeded, savedResultToShow);
				handler.sendMessage(message);
			}
			savedResultToShow = null;
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (holder == null) {
			// Log.e(TAG,"*** WARNING *** surfaceCreated() gave us a null surface!");
		}
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	protected void onPause() {
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		inactivityTimer.onPause();
		cameraManager.closeDriver();
		if (!hasSurface) {
			SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
			SurfaceHolder surfaceHolder = surfaceView.getHolder();
			surfaceHolder.removeCallback(this);
		}
		super.onPause();
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();
	}

	private void resetStatusView() {
		resultView.setVisibility(View.GONE);
		statusView.setText("");
		statusView.setVisibility(View.VISIBLE);
		viewfinderView.setVisibility(View.VISIBLE);
		cameraManager.setTorch(mIsFlashLightOpen);
		mBackBtn.setVisibility(View.VISIBLE);
		mFlashlight.setVisibility(View.VISIBLE);
		mClose.setVisibility(View.GONE);
		lastResult = null;
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (source == IntentSource.NATIVE_APP_INTENT) {
				setResult(RESULT_CANCELED);
				finish();
				return true;
			}
			if ((source == IntentSource.NONE || source == IntentSource.ZXING_LINK) && lastResult != null) {
				restartPreviewAfterDelay(0L);
			
				return true;
			}
			break;
		case KeyEvent.KEYCODE_FOCUS:
		case KeyEvent.KEYCODE_CAMERA:
			// Handle these events so they don't launch the Camera app
			return true;
			// Use volume up/down to turn on light
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			cameraManager.setTorch(false);
			return true;
		case KeyEvent.KEYCODE_VOLUME_UP:
			cameraManager.setTorch(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
