package com.example.master.sundarbantourists;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
WebView siteView;
String url="https://www.sundarbantourist.com/";
ViewPager viewPager;
    private Integer [] images={R.drawable.bakkhali_beach,
            R.drawable.sundarbans_crabs,
            R.drawable.sundarbans_mangroveforest,
            R.drawable.sundarbans1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ViewPagerAdapter adapter=new ViewPagerAdapter(this,images);
        viewPager.setAdapter(adapter);
        siteView.setWebViewClient(new WebViewClient());
        siteView.getSettings().setJavaScriptEnabled(true);
        siteView.loadUrl(url);
    }

    private void initView() {
        viewPager=findViewById(R.id.viewPager);
        siteView=findViewById(R.id.siteView);
    }
}
