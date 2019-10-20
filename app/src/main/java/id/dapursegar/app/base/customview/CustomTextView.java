package id.dapursegar.app.base.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import id.dapursegar.app.R;

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTypeface(Typeface tf, int style) {
        Typeface boldTypeface = ResourcesCompat.getFont(this.getContext(), R.font.os_bold);
        Typeface iconTypeFace = ResourcesCompat.getFont(this.getContext(), R.font.ionicons);
        Typeface normalTypeface = ResourcesCompat.getFont(this.getContext(), R.font.os_regular);

        switch (style) {
            case Typeface.BOLD:
                super.setTypeface(boldTypeface/*, -1*/);
                break;
            case Typeface.ITALIC:
                super.setTypeface(iconTypeFace/*, -1*/);
                break;
            default:
                super.setTypeface(normalTypeface/*, -1*/);
                break;
        }
    }
}
