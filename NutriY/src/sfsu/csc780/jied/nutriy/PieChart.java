package sfsu.csc780.jied.nutriy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Custom view that shows a pie chart of Calorie.
 */
public class PieChart extends View {
	
	private boolean mShowChart;
	private float mPieRadius;
	private float mDividerWidth;
	
	private Paint mTextPaint;
	private int mTextColor;
	private float mTextSize;
	
	private int mEmptyPieColor;
	private int mCarbPieColor;
	private int mFatPieColor;
	private int mProteinPieColor;
	
	private Paint mEmptyPiePaint;
	private Paint mCarbPiePaint;
	private Paint mFatPiePaint;
	private Paint mProteinPiePaint;
	
	private double mCarbPercentage;
	private double mFatPercentage;
	private double mProteinPercentage;
	
	private RectF mPieBound;
	
	public PieChart(Context context) {
		super(context);
	}

	/**
     * Class constructor taking a context and an attribute set. This constructor
     * is used by the layout engine to construct a {@link PieChart} from a set of
     * XML attributes.
     *
     * @param context
     * @param attrs   An attribute set which can contain attributes from
     *                {@link sfsu.csc780.jied.nutriy.R.styleable.PieChart} as well as attributes inherited
     *                from {@link android.view.View}.
     */
	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// attrs contains the raw values for the XML attributes
        // that were specified in the layout, which don't include
        // attributes set by styles or themes, and which may have
        // unresolved references. Call obtainStyledAttributes()
        // to get the final values for each attribute.
        //
        // This call uses R.styleable.PieChart, which is an array of
        // the custom attributes that were declared in attrs.xml.
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieChart,
                0, 0
        );
        
        try {
            mShowChart = a.getBoolean(R.styleable.PieChart_showChart, false);
            mPieRadius = a.getDimension(R.styleable.PieChart_pieRadius, 75.0f);
            mDividerWidth = a.getDimension(R.styleable.PieChart_dividerWidth, 1.0f);
            mEmptyPieColor = a.getColor(R.styleable.PieChart_emptyPieColor, 0XFF8FCEFF);
            mCarbPieColor = a.getColor(R.styleable.PieChart_carbPieColor, 0xFF0000FF);
            mFatPieColor = a.getColor(R.styleable.PieChart_fatPieColor, 0xFFFF0000);
            mProteinPieColor = a.getColor(R.styleable.PieChart_proteinPieColor, 0xFF00FF00);
            mCarbPercentage = a.getInteger(R.styleable.PieChart_carbPercentage, 50);
            mFatPercentage = a.getInteger(R.styleable.PieChart_fatPercentage, 30);
            mProteinPercentage = a.getInteger(R.styleable.PieChart_proteinPercentage, 20);
        } finally {
            a.recycle();
        }
        
		init();
	}
	
	private void init() {
		
		mTextColor = getResources().getColor(R.color.white);
		mTextSize = getResources().getDimension(R.dimen.medium_large_text_size);
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    mTextPaint.setColor(mTextColor);
	    mTextPaint.setTextSize(mTextSize);

	    mEmptyPiePaint = new Paint(0);
	    mEmptyPiePaint.setColor(mEmptyPieColor);
	    mEmptyPiePaint.setStyle(Paint.Style.FILL);
	    
	    mCarbPiePaint = new Paint(0);
	    mCarbPiePaint.setColor(mCarbPieColor);
	    mCarbPiePaint.setStyle(Paint.Style.FILL);
	    
	    mFatPiePaint = new Paint(0);
	    mFatPiePaint.setColor(mFatPieColor);
	    mFatPiePaint.setStyle(Paint.Style.FILL);
	    
	    mProteinPiePaint = new Paint(0);
	    mProteinPiePaint.setColor(mProteinPieColor);
	    mProteinPiePaint.setStyle(Paint.Style.FILL);
	    
	    mPieBound = new RectF(0.0f, 0.0f, mPieRadius * 2, mPieRadius * 2);
	    mPieBound.offsetTo(getWidth() / 2 +  mPieRadius, 10.0f);
	    //mPieBound.offset(getWidth() / 2, 10.0f);
	}
	
	public boolean isShowChart() {
	   return mShowChart;
	}

	public void setShowChart(boolean showChart) {
	   mShowChart = showChart;
	   invalidate();
	   requestLayout();
	}
	
	protected void onDraw(Canvas canvas) {
	   super.onDraw(canvas);
	   
	   float cy = mPieRadius + 10.0f;
	   float cx = getWidth() / 2.0f;
	   
	   if (mShowChart) {
		   canvas.drawArc(mPieBound, 0, -180, true, mCarbPiePaint);
		   canvas.drawArc(mPieBound, 180, -108, true, mFatPiePaint);
		   canvas.drawArc(mPieBound, 0, 72, true, mProteinPiePaint);
	   } else {
		   canvas.drawCircle(cx, cy, mPieRadius, mEmptyPiePaint);
	   }
	      

	   /** Draw the pie slices
	   for (int i = 0; i < mData.size(); ++i) {
	       Item it = mData.get(i);
	       mPiePaint.setShader(it.mShader);
	       canvas.drawArc(mBounds,
	               360 - it.mEndAngle,
	               it.mEndAngle - it.mStartAngle,
	               true, mPiePaint);
	   }*/
	}
}