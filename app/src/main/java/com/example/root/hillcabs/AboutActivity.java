package com.example.root.hillcabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.ic_launcher)

                .addGroup("Connect with us")
                .addEmail("http://www.hillclick.in/")
                .addWebsite("http://www.hillclick.in/")
                .addFacebook("FavAdd")
                .addTwitter("kumardev.sha")
                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addInstagram("")
                .addItem(getCopyRightsElement())
                .create();

        setContentView(aboutPage);
    }


    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIcon(R.drawable.about_icon_copy_right);
        copyRightsElement.setColor(ContextCompat.getColor(this, mehdi.sakout.aboutpage.R.color.about_item_icon_color));
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutActivity.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}
