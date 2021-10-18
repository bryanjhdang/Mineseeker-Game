package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Help activity to display game descriptions, authors, and
 * sources for images and resources
 */
public class HelpActivity extends AppCompatActivity {

    public static Intent launchIntent(Context c) {
        Intent intent = new Intent(c, HelpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeInitialBars();
        setContentView(R.layout.activity_help);

        TextView gameDescription = (TextView) findViewById(R.id.descriptionText);
        String formattedDescription = "<b>Cat Seeker</b>" +
                "<br> Player can choose the size of the game board and number of cats hidden. " +
                "Tap any button to search for the cat. If the cat is there, well done. " +
                "Otherwise, player will start the scanner that shows the number of hidden cats in that row and column.</br>";
        gameDescription.setText(HtmlCompat.fromHtml(formattedDescription, HtmlCompat.FROM_HTML_MODE_LEGACY));

        TextView gameAuthor = (TextView) findViewById(R.id.authorText);
        String formattedAuthor = "<b>Author</b>" +
                "<br>Written by Bryan Dang and Lynn Nguyen, students in Intro for Software Engineering " +
                "(<a href='https://opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/home'>https://opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/home</a>)</br>";
        gameAuthor.setText(HtmlCompat.fromHtml(formattedAuthor, HtmlCompat.FROM_HTML_MODE_LEGACY));
        gameAuthor.setMovementMethod(LinkMovementMethod.getInstance());

        TextView gameSource = (TextView) findViewById(R.id.sourcesText);
        String formattedSource = "<b>Resources</b>" +
                "<br>Theme inspired by the game Neko Atsume " +
                "(<a href='https://www.nekoatsume.com/en/'>https://www.nekoatsume.com/</a>)</br>" +
                "<br>Icons from CLEANPNG" +
                "(<a href='https://www.cleanpng.com/'>https://www.cleanpng.com/</a>)</br>" +
                "<br>Images from Pinterest" +
                "(<a href='https://www.pinterest.com/'>https://www.pinterest.com/</a>)</br>";
        gameSource.setText(HtmlCompat.fromHtml(formattedSource, HtmlCompat.FROM_HTML_MODE_LEGACY));
        gameSource.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // https://www.youtube.com/watch?v=jOWW95u15S0&ab_channel=TechProjects
    private void removeInitialBars() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // make it so that the text is for each of the description upon creation
}