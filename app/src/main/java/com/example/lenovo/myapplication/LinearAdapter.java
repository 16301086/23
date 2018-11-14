package com.example.lenovo.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * created by Qin yiyi 16301087@bjtu.edu.cn
 */

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {

    private Context mcontext;

    /**
     * 构造函数，需要的值可在这儿传过来
     */
    public LinearAdapter(Context context){
        mcontext = context;
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     */
    @NonNull
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = View.inflate(mcontext, R.layout.recycler_linear_item, null);
        return new LinearViewHolder(itemView);
        //return new LinearViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.recycler_linear_item,viewGroup,false));
    }

    /**
     * 绑定数据，数据与view绑定
     */
    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.LinearViewHolder viewHolder, int position) {
        position++;
        String s = "↑ Class ";
        s += String.valueOf(position);
        viewHolder.textView.setText(s);
    }

    /**
     * 得到总条数
     */
    @Override
    public int getItemCount() {
        return 10;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        ImageButton button;
        TextView textView;
        CardView cardView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.imageButton_);
            textView = itemView.findViewById(R.id.textView_);
            cardView = itemView.findViewById(R.id.cardview);
            View.OnClickListener l = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = (String) textView.getText();
                    str +=" is not open at present";
                    Toast.makeText(mcontext,str,Toast.LENGTH_SHORT).show();
                    /*此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, goodsEntityList.get(getLayoutPosition()));
                    }*/
                }
            };
            cardView.setOnClickListener(l);
            button.setOnClickListener(l);

        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         */
        public void OnItemClick(View view);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
