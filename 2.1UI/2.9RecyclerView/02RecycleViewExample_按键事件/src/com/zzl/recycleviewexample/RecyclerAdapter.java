package com.zzl.recycleviewexample;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;

import android.view.ViewGroup;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder> {

	private String[] mDataset;
	//按键事件 start
	private OnItemClickListener mOnItemClickListener;
	public interface OnItemClickListener{
		void onClick(int position);
		void onLongClick(int position);
	}
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
		this.mOnItemClickListener=onItemClickListener;
	}
	//按键事件 end
	public RecyclerAdapter(String[] dataset) {

		mDataset = dataset;

	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView mTextView;

		

		public ViewHolder(View itemView) {

			super(itemView);

			mTextView = (TextView) itemView;

		}

	}

	

	@Override
	public int getItemCount() {

		return mDataset.length;

	}

	

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {

		holder.mTextView.setText(mDataset[position]);
//短按事件
		if(mOnItemClickListener!=null){
			holder.itemView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mOnItemClickListener.onClick(position);
				}
			});
//长按事件			
			holder.itemView.setOnLongClickListener(new OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					mOnItemClickListener.onLongClick(position);
					return false;
				}
			});
		}
		
	}

	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = View.inflate(parent.getContext(),

		android.R.layout.simple_list_item_1, null);

		ViewHolder holder = new ViewHolder(view);

		return holder;

	}

}
