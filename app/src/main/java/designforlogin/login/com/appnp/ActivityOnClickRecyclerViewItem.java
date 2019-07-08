package designforlogin.login.com.appnp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import designforlogin.login.com.appnp.Adapters.TestAdapter;

public class ActivityOnClickRecyclerViewItem extends AppCompatActivity implements TestInterface {
    TextView txtNameOnItemClickRecycleView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_recycler_view_item);


//        txtNameOnItemClickRecycleView=findViewById(R.id.txtNameOnItemClickRecycleView);
//        Intent intent= getIntent();
//        AdapterTopSellSubData subDatas=(AdapterTopSellSubData)intent.getSerializableExtra("Item_Details");
        RecyclerView testRecyclerview= findViewById(R.id.testRecyclerView);
//        if(subDatas!=null) {
//            txtNameOnItemClickRecycleView.setText(subDatas.getTopSellsubText());
//        }
        context = getApplicationContext();
        ArrayList<TestData> tesDataList = new ArrayList<>();
        tesDataList.add(new TestData("data1","data1"));
        tesDataList.add(new TestData("data2","data2"));
        tesDataList.add(new TestData("data1","data1"));
        String myStrigToPass = "kathmandu";
        TestAdapter ta = new TestAdapter(myStrigToPass,this );
        testRecyclerview.hasFixedSize();
        testRecyclerview.setLayoutManager(new LinearLayoutManager((context)));
        testRecyclerview.setAdapter(ta);




    }

    @Override
    public void myViewHolderPosition(int i) {
        Toast.makeText(this,"this view"+i,Toast.LENGTH_SHORT).show();
    }
}
