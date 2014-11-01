package com.example.viewpagermultipages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ViewPager mPager;
	private float MIN_SCALE = 1f - 1f / 7f;
	private float MAX_SCALE = 1f;
	private int MIN_ROTATION = 0;
	private int MAX_ROTATION = 20;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(new PagerAdapter() {
			private boolean mIsDefaultItemSelected = true;

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 10;
			}

			@Override
			public Object instantiateItem(View collection,final int position) {
				// TODO Auto-generated method stub
				final ImageView cardImageView = new ImageView(MainActivity.this);
				int drawableWidth = getResources().getDrawable(R.drawable.ic_launcher).getIntrinsicWidth();
				int drawableHeight = getResources().getDrawable(R.drawable.ic_launcher).getIntrinsicHeight();
				int width = getResources().getDisplayMetrics().widthPixels/3;
				System.out.println(width);
				cardImageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				if(position!=0&&position!=9)
					cardImageView.setImageResource(R.drawable.sample);
				((ViewPager) collection).addView(cardImageView);
				cardImageView.setTag(position);
				if (!mIsDefaultItemSelected) {
					cardImageView.setScaleX(MAX_SCALE);
					cardImageView.setScaleY(MAX_SCALE);
					mIsDefaultItemSelected = true;
				} else {
					cardImageView.setScaleX(MIN_SCALE);
					cardImageView.setScaleY(MIN_SCALE);
					//cardImageView.setRotationY(MIN_ROTATION);
				}
				
				cardImageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if(cardImageView.getScaleX() <= MAX_SCALE&&cardImageView.getScaleX() > MIN_SCALE) {
							Toast.makeText(MainActivity.this,"item clicked = " + cardImageView.getTag(),Toast.LENGTH_SHORT).show();
						}
					}
				});
				
				return cardImageView;
			}

			@Override
			public float getPageWidth(int position) {
				// TODO Auto-generated method stub
				return 0.33f;
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				// TODO Auto-generated method stub
				//super.destroyItem(container, position, object);
				mPager.removeView((View)object);
			}
		});

		
		
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				Log.d("______________", "______________");
				Log.e("position", ""+position);
				Log.e("pageOffset", ""+positionOffset);
				Log.e("positionOffsetPixels", ""+positionOffsetPixels);

				for (int i = 0; i < mPager.getChildCount(); i++) {
					final View cardView = mPager.getChildAt(i);
					int itemPosition = (Integer) cardView.getTag();
                    
					if(itemPosition == position) {

					}
					
					if (itemPosition == position+1) {
						cardView.setScaleX(MAX_SCALE - positionOffset / 7f);
						cardView.setScaleY(MAX_SCALE - positionOffset / 7f);
						//cardView.setRotationY(-MAX_ROTATION*positionOffset);
						
					}

					if (itemPosition == (position+2)) {
						cardView.setScaleX(MIN_SCALE + positionOffset / 7f);
						cardView.setScaleY(MIN_SCALE + positionOffset / 7f);
						//cardView.setRotationY(MAX_ROTATION*positionOffset);
						cardView.setOnClickListener(null);
					}
				}
				//if()
				//mPager.getChildAt(position)

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClickWhatsApp(View view) {

	    Intent waIntent = new Intent(Intent.ACTION_SEND);
	    waIntent.setType("text/plain");
	            String text = "YOUR TEXT HERE";
	    waIntent.setPackage("com.whatsapp");
	    if (waIntent != null) {
	        waIntent.putExtra(Intent.EXTRA_TEXT, text);
	        startActivity(Intent.createChooser(waIntent, "Share with"));
	    } else {
	        Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
	                .show();
	    }

	}
	
}
