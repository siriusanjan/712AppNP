package designforlogin.login.com.appnp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.TestInterface;

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //ArrayList<TestData> newTestArray;
//public TestAdapter(ArrayList<TestData> newTestArray){
//    this.newTestArray=newTestArray;
//
//}
    String myStringData;
    TestInterface testInterfacae;

    public TestAdapter(String myString, TestInterface testInterface) {
        myStringData = myString;
        this.testInterfacae = testInterface;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
//    TestData a= newTestArray.get(i);
//        ((MyViewHolder) viewHolder).txtTest1.setText(a.getTest1());
//        ((MyViewHolder) viewHolder).txtTest2.setText(a.getTest2());
        if (viewHolder instanceof MyViewHolder) {
            ((MyViewHolder) viewHolder).txtTest1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    testInterfacae.myViewHolderPosition(i);


                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.test_adapter_layout;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTest1, txtTest2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTest1 = itemView.findViewById(R.id.txtTextTest1);
            txtTest2 = itemView.findViewById(R.id.txtTextTest2);
        }
    }
}
