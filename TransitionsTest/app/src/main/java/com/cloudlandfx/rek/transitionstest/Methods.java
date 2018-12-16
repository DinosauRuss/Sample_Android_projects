package com.cloudlandfx.rek.transitionstest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

public class Methods extends AppCompatActivity {

        // Constant used for the Intent to indicate the type of transition.
        private static final String TRANSITION_TYPE = "Transition Type";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Get the transition type from the Intent and set it.
            if (getIntent().hasExtra(TRANSITION_TYPE)) {
                switch (getIntent().getStringExtra(TRANSITION_TYPE)) {
                    case "Explode":
                        getWindow().setEnterTransition(new Explode());
                        break;
                    case "Slide":
                        getWindow().setEnterTransition(new Slide());
                        break;
                    case "Fade":
                        getWindow().setEnterTransition(new Fade());
                        break;
                    default:
                        break;
                }
            }
        }

        /**
         * Relaunch the activity with an Explode animation.
         *
         * @param context The application context.
         * @param imageView The imageView that was clicked.
         */
        protected void recreateExplode(final Context context, ImageView imageView) {

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Relaunch the activity with the transition information.
                    Intent intento = new Intent(context,context.getClass());
                    intento.putExtra(TRANSITION_TYPE,"Explode");
                    getWindow().setExitTransition(new Explode());
                    startActivity(intento, ActivityOptions.
                            makeSceneTransitionAnimation(
                                    (Activity)context).toBundle());

                }
            });
        }

        protected void recreateFade(final Context context, ImageView imageview) {

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intento = new Intent(context, context.getClass());
                    intento.putExtra(TRANSITION_TYPE, "Fade");
                    getWindow().setExitTransition(new Fade());
                    startActivity(intento, ActivityOptions.makeSceneTransitionAnimation(
                            (Activity) context).toBundle());
                }
            });
        }

        protected void startSharedElementActivity(final Context context, ImageView imageview,
                                                  final ImageView sharedElement) {

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intento = new Intent(context, SecondActivity.class);
                    // Create the animation
                    // Each shared element has transitionName "greenDroid"
                    ActivityOptions opto = ActivityOptions.makeSceneTransitionAnimation(
                            (Activity) context, sharedElement, "greenDroid");
                    startActivity(intento, opto.toBundle());
                }
            });
        }

        protected void rotateView(ImageView imageView) {
            final ObjectAnimator animato = ObjectAnimator.ofFloat(imageView, View.ROTATION,
                    0, 360);
            animato.setRepeatMode(ValueAnimator.REVERSE);
            animato.setRepeatCount(1);
            animato.setDuration(1500);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animato.start();
                }
            });

        }

}
