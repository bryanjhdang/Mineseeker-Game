package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Initial activity displayed at startup.
 * Displays welcome message and an option to move to the main menu.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeInitialBars();
        setContentView(R.layout.activity_main);

        moveCenterCatAcrossScreen();
        rotateAllIcons();
        setMenuButton();
    }

    // https://www.youtube.com/watch?v=jOWW95u15S0&ab_channel=TechProjects
    private void removeInitialBars() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setMenuButton() {
        Button menuBtn = findViewById(R.id.skipButton);
        menuBtn.setTextColor(0xFF000000);
        menuBtn.setOnClickListener(view -> {
            Intent i = MenuActivity.launchIntent(WelcomeActivity.this);
            startActivity(i);
            finish();
        });
    }

    // https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android
    private float getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    // https://stackoverflow.com/questions/7950383/how-to-move-images-from-left-to-right-in-android
    private void moveCenterCatAcrossScreen() {
        final int animTimeInMilliseconds = 5000;

        ImageView boxCat = findViewById(R.id.boxCat);
        final int catWidth = boxCat.getLayoutParams().width;

        // Create animation of cat moving across the screen
        TranslateAnimation anim = new TranslateAnimation(0.0f,
                getScreenWidth() + catWidth,
                0.0f,
                0.0f);
        anim.setDuration(animTimeInMilliseconds);
        boxCat.startAnimation(anim);

        // Check to see if the animation finished, change activities if so
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent i = MenuActivity.launchIntent(WelcomeActivity.this);
                startActivity(i);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    /**
     * Calling method to rotate the two cat head icons
     * in the activity
     */
    private void rotateAllIcons() {
        rotateIcon(findViewById(R.id.catHead1), -360f);
        rotateIcon(findViewById(R.id.catHead2), 360f);
    }

    /**
     * Rotate an image 360 degrees continuously
     * @param img ImageView to rotate
     * @param deg for the amount of degrees to rotate the image
     */
    // https://stackoverflow.com/questions/32641150/how-to-make-imageview-constantly-spin
    private void rotateIcon(ImageView img, float deg) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, deg,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        img.startAnimation(rotateAnimation);
    }
}