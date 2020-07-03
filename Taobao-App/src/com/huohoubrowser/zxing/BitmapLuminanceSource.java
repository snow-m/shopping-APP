/*
 * Copyright 2009 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huohoubrowser.zxing;

import android.graphics.Bitmap;

/**
 * BitmapLuminanceSource
 * 
 */
public final class BitmapLuminanceSource extends LuminanceSource {

	private final byte[] bitmapPixels;

	public BitmapLuminanceSource(Bitmap bitmap) {
		super(bitmap.getWidth(), bitmap.getHeight());

		int[] data = new int[bitmap.getWidth() * bitmap.getHeight()];
		this.bitmapPixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
		bitmap.getPixels(data, 0, getWidth(), 0, 0, getWidth(), getHeight());

		for (int i = 0; i < data.length; i++) {
			this.bitmapPixels[i] = (byte) data[i];
		}
	}

	@Override
	public byte[] getMatrix() {
		return bitmapPixels;
	}

	@Override
	public byte[] getRow(int y, byte[] row) {
		System.arraycopy(bitmapPixels, y * getWidth(), row, 0, getWidth());
		return row;
	}
}
