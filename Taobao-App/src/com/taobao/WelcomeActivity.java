package com.taobao;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.exception.DbException;
import com.taobao.entity.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

/**
 * @author 作者 :Duncan Wei
 * @version 创建时间：2013-12-9 上午08:58:11
 * 类说明
 */

public class WelcomeActivity extends BaseActivity{
	ImageView bg_mageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		bg_mageView=(ImageView) findViewById(R.id.hotEvent_image);
		bg_mageView.setImageResource(R.drawable.welcome_bgimage);
		//(热卖、最新、优惠、推荐、其他)
//		try {
//			List<Shop> shops = dbUtils.findAll(Shop.class);
//			if(shops==null||shops!=null&&shops.size()==0){
//				shops = new ArrayList<Shop>();
//				//服装内衣
//				shops.add(new Shop(R.drawable.pic1,R.drawable.pic2, "2014新款夏季大码印花圆点中袖雪纺连衣裙显瘦千鸟格裙子韩版潮；", "68", "服装内衣", "最新"));
//				shops.add(new Shop(R.drawable.pic3,R.drawable.pic4, "春季新品 睡衣 女 纯棉 长袖情侣睡衣套装女人睡衣休闲家居服；", "46", "服装内衣", "热卖"));
//				shops.add(new Shop(R.drawable.pic5,R.drawable.pic6, "夏季黑丝袜包芯丝打底袜女士连裤丝袜子防勾丝加档瘦腿性感超薄款", "5.8", "服装内衣", "热卖"));
//				shops.add(new Shop(R.drawable.pic7,R.drawable.pic8, "2014新款夏季日系森女甜美梅花鹿短袖T恤宽松中长款雪纺衫；", "36", "服装内衣", "最新"));
//				shops.add(new Shop(R.drawable.pic9,R.drawable.pic10, "夏季新款纯棉华夫格男女睡袍 柔软舒适浴袍 情侣七分袖睡衣；", "36", "服装内衣", "热卖"));
//				
//				//鞋包配饰
//				shops.add(new Shop(R.drawable.pic11,R.drawable.pic12, "春季韩版潮 女帆布鞋 高帮包边休闲透气 女鞋 学生鞋；", "45", "鞋包配饰", "优惠"));
//				shops.add(new Shop(R.drawable.pic13,R.drawable.pic14, "正品2014新款欧美时尚女包斜跨手提包中年女式包鳄鱼纹 潮流女包；", "93", "鞋包配饰", "推荐"));
//				shops.add(new Shop(R.drawable.pic15,R.drawable.pic16, "远港丝纺 真丝小方巾 正品女士桑蚕丝高档丝巾；", "28", "鞋包配饰", "热卖"));
//				shops.add(new Shop(R.drawable.pic17,R.drawable.pic18, "天然水晶六字真言开运情侣108佛珠手串 玉髓红玛瑙手链；", "19", "鞋包配饰", "其他"));
//				
//				//家居生活
//				shops.add(new Shop(R.drawable.pic19,R.drawable.pic20, "情侣定制刻字碗筷特别创意送女友男友实用时尚结婚礼物摆件；", "98", "家居生活", "推荐"));
//				shops.add(new Shop(R.drawable.pic21,R.drawable.pic22, "正品夏凉被蚕丝被空调被 可水洗单人双人被薄被纯棉夏；", "128", "家居生活", "最新"));
//				shops.add(new Shop(R.drawable.pic23,R.drawable.pic24, "平底锅无烟不粘锅26CM煎锅煎盘炒锅煎炒 电磁炉通用煎锅；", "39", "家居生活", "优惠"));
//				shops.add(new Shop(R.drawable.pic25,R.drawable.pic26, "保士洁全能速净衣物清洁/去除油污/布艺地毯玻璃窗/油烟机清洁剂；", "32", "家居生活", "推荐"));
//				
//				//护肤彩妆
//				shops.add(new Shop(R.drawable.pic27,R.drawable.pic28, "正品普拉提娜胶原蛋白水晶面膜 补水面膜 美白面膜 面膜贴9945；", "2", "护肤彩妆", "推荐"));
//				shops.add(new Shop(R.drawable.pic29,R.drawable.pic30, "18秒快干28色多色指甲油 超多色 带香味 BK18秒快干指甲油；", "1.8", "护肤彩妆", "热卖"));
//				shops.add(new Shop(R.drawable.pic31,R.drawable.pic32, "正品男士香水持久淡香古龙水 男用古龙香水清新男50ml；", "75", "护肤彩妆", "热卖"));
//				shops.add(new Shop(R.drawable.pic33,R.drawable.pic34, "依然美假发新品 假刘海 时尚卷发卷刘海片 假发片接发片；", "24", "护肤彩妆", "优惠"));
//				
//				//美食特产
//				shops.add(new Shop(R.drawable.pic35,R.drawable.pic36, "好纯营养麦片燕麦巧克力糖客喜糖果特产零食500g 小吃低糖美食；", "9.9", "美食特产", "优惠"));
//				shops.add(new Shop(R.drawable.pic37,R.drawable.pic38, "茶叶 新安铁观音 乌龙茶 特级安溪铁观音 华虹；", "19", "美食特产", "热卖"));
//				shops.add(new Shop(R.drawable.pic39,R.drawable.pic40, "法国红酒原平进口红酒 葡萄酒干红葡萄酒进口红酒；", "39", "美食特产", "最新"));
//				shops.add(new Shop(R.drawable.pic41,R.drawable.pic42, "柯林蓝山咖啡豆 原装进口中美拼配新鲜烘焙 现磨纯黑咖啡粉454g；", "52", "美食特产", "优惠"));
//				
//				//手机数码
//				shops.add(new Shop(R.drawable.pic43,R.drawable.pic44, "正品Samsung/三星 GT-I9082双卡双核手机安卓智能手机；", "799", "手机数码", "优惠"));
//				shops.add(new Shop(R.drawable.pic45,R.drawable.pic46, "本易 Miracleone 10寸IPS三星四核平板1G/16G高端四核平板电脑；", "999", "手机数码", "推荐"));
//				shops.add(new Shop(R.drawable.pic47,R.drawable.pic48, "正品 全国联保 Sony/索尼WX200 数码相机 索尼DSC-WX200；", "830", "手机数码", "热卖"));
//				shops.add(new Shop(R.drawable.pic49,R.drawable.pic50, "TP-LINK 无线路由器穿墙王 TL-WR842N 300M迷你无线wifi；", "89.8", "手机数码", "最新"));
//				dbUtils.saveAll(shops);
//			}
//		} catch (DbException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Handler handler=new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
				WelcomeActivity.this.startActivity(intent);
				WelcomeActivity.this.finish();
			}
		},3000);
	}



}
