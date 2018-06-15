package com.manmeet.droidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.manmeet.droidme.R;
import com.manmeet.droidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droidme_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setImageList(AndroidImageAssets.getHeads());
            int headIndex = getIntent().getIntExtra("head",0);
            headFragment.setImageListIndex(headIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageList(AndroidImageAssets.getBodies());
            int bodyIndex = getIntent().getIntExtra("body",0);
            bodyFragment.setImageListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageList(AndroidImageAssets.getLegs());
            int legIndex = getIntent().getIntExtra("leg",0);
            legFragment.setImageListIndex(legIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
