package com.manmeet.droidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.manmeet.droidme.R;
import com.manmeet.droidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            Button button = findViewById(R.id.next_button);
            button.setVisibility(View.GONE);

            GridView gridView = findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageList(AndroidImageAssets.getHeads());
                int headIndex = getIntent().getIntExtra("head", 0);
                headFragment.setImageListIndex(headIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageList(AndroidImageAssets.getBodies());
                int bodyIndex = getIntent().getIntExtra("body", 0);
                bodyFragment.setImageListIndex(bodyIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageList(AndroidImageAssets.getLegs());
                int legIndex = getIntent().getIntExtra("leg", 0);
                legFragment.setImageListIndex(legIndex);
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        int bodyPart = position / 12;
        int listIndex = position - 12 * bodyPart;

        if (mTwoPane) {
            BodyPartFragment newBodyPartFragment = new BodyPartFragment();
            switch (bodyPart){
                case 0:
                    newBodyPartFragment.setImageList(AndroidImageAssets.getHeads());
                    newBodyPartFragment.setImageListIndex(listIndex);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container,newBodyPartFragment)
                            .commit();
                    break;
                case 1:
                    newBodyPartFragment.setImageList(AndroidImageAssets.getBodies());
                    newBodyPartFragment.setImageListIndex(listIndex);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container,newBodyPartFragment)
                            .commit();
                    break;

                case 2:
                    newBodyPartFragment.setImageList(AndroidImageAssets.getLegs());
                    newBodyPartFragment.setImageListIndex(listIndex);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container,newBodyPartFragment)
                            .commit();
                    break;


            }
        } else {
            switch (bodyPart) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("head", headIndex);
        bundle.putInt("body", bodyIndex);
        bundle.putInt("leg", legIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
