package co.typecheck.android.toolkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ImageView;

import co.typecheck.android.toolkit.R;

public class RotatingImageView extends ImageView {

    private static final int DEFAULT_DURATION = 1000;

    private final float mAngularSpeed;

    private float mCurrentRotation;
    private long mPreviousTimestamp;

    public RotatingImageView(Context context) {
        super(context);

        mPreviousTimestamp = SystemClock.elapsedRealtime();
        mCurrentRotation = 0.0f;

        mAngularSpeed = fromDurationToAngularSpeed(DEFAULT_DURATION);
    }

    public RotatingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotatingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPreviousTimestamp = SystemClock.elapsedRealtime();
        mCurrentRotation = 0.0f;

        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RotatingImageView,
                0, 0);

        try {
            int duration = a.getInteger(R.styleable.RotatingImageView_duration, DEFAULT_DURATION);
            mAngularSpeed = fromDurationToAngularSpeed(duration);
        } finally {
            a.recycle();
        }
    }

    private static float fromDurationToAngularSpeed(int duration) {
        return (360f * DEFAULT_DURATION) / duration;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long currentTime = SystemClock.elapsedRealtime();
        float timeDelta = (currentTime - mPreviousTimestamp) / 1000f;
        mPreviousTimestamp = currentTime;

        mCurrentRotation = (mCurrentRotation + (mAngularSpeed * timeDelta)) % 360;

        canvas.save();
        canvas.rotate(mCurrentRotation, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
        canvas.restore();

        invalidate();
    }
}
