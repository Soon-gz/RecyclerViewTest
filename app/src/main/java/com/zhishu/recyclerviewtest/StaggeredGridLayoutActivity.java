package com.zhishu.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;

public class StaggeredGridLayoutActivity extends AppCompatActivity
{

	@Bind(R.id.id_recyclerview)
	RecyclerView mRecyclerView;
	private List<String> mDatas;
	private StaggeredHomeAdapter mStaggeredHomeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_recyclerview);
		ButterKnife.bind(this);
		initData();

		mStaggeredHomeAdapter = new StaggeredHomeAdapter(this, mDatas);

		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
				StaggeredGridLayoutManager.VERTICAL));
		mRecyclerView.setAdapter(mStaggeredHomeAdapter);
		// 设置item动画
		mRecyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(mRecyclerView));

		initEvent();

	}

	private void initEvent()
	{
		mStaggeredHomeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(StaggeredGridLayoutActivity.this, "Item点击", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(StaggeredGridLayoutActivity.this, "Item长点击", Toast.LENGTH_SHORT).show();
			}
		});
	}

	protected void initData()
	{
		mDatas = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++)
		{
			mDatas.add("" + (char) i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main_staggered, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.id_action_add:
			mStaggeredHomeAdapter.addData(1);
			break;
		case R.id.id_action_delete:
			mStaggeredHomeAdapter.removeData(1);
			break;
		}
		return true;
	}

}
