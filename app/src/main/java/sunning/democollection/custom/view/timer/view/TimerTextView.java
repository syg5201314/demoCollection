package sunning.democollection.custom.view.timer.view;
/**
 * @author harvic
 * @address blog.csdn.net/harvic880925
 * @date 2014-12-17
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TimerTextView extends TextView implements Runnable{
	
	public TimerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

    private long mday, mhour, mmin, msecond;//�죬Сʱ�����ӣ���
    private boolean run=false; //�Ƿ�������

    public void setTimes(long[] times) {
        mday = times[0];
        mhour = times[1];
        mmin = times[2];
        msecond = times[3];

    }

	private void ComputeTime() {
	    msecond--;
	    if (msecond < 0) {
	        mmin--;
	        msecond = 59;
	        if (mmin < 0) {
	            mmin = 59;
	            mhour--;
	            if (mhour < 0) {
	                // ����ʱ����һ����24��Сʱ
	                mhour = 23;
	                mday--;
	
	            }
	        }
	
	    }
	
	}

    public boolean isRun() {
        return run;
    }

    public void beginRun() {
        this.run = true;
        run();
    }
    
    public void stopRun(){
    	this.run = false;
    }
    

    @Override
    public void run() {
        //��ʾ�Ѿ�����
        if(run){
        	ComputeTime();

            String strTime= mday +"天"+ mhour+"时:"+ mmin+"分"+msecond+"秒";
            this.setText(strTime);

            postDelayed(this, 1000);
        }else {
        	removeCallbacks(this);
        }
    }

}
