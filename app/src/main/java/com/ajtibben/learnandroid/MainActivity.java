package com.ajtibben.learnandroid;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.view.View.*;
import android.content.*;
import java.io.*;

public class MainActivity extends Activity 
{
	static final int OPEN_REQUEST=1301;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		registerHandlers();
    }
	

	private void registerHandlers()
	{
		Button b=(Button) findViewById(R.id.button);
		b.setOnClickListener(new OnClickListener() {

				public void onClick(View v)
				{
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_GET_CONTENT);
					intent.setType("*/*");
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					startActivityForResult(intent, OPEN_REQUEST);
				};
			});
		Button closeButton = (Button) findViewById(R.id.close);
		closeButton.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					finish();
				}


			});

//		ListView lineList = (ListView) findViewById(R.id.listView);
//		lineList.setAdapter(lineAdapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == OPEN_REQUEST) {
			if (resultCode == RESULT_OK) {
				TextView inhoud = (TextView) findViewById(R.id.inhoud);

				try {
					inhoud.setText(data.getDataString());
					
					InputStream is = getContentResolver().openInputStream(data.getData());
					BufferedReader reader = new BufferedReader(
						new InputStreamReader(is)
					);
					
					inhoud.setText(reader.readLine());
					
					reader.close();
					/*
					
					MT940Reader parser = new MT940Reader(reader);

					lines.clear();

					parser.parseInto(lines);

					is.close();

					inhoud.setText("ok");
					*/
				}
				catch (FileNotFoundException e)
				{
					inhoud.setText(e.toString());
				}
				catch (IOException e)
				{
					inhoud.setText(e.toString());
				}
				
				//lineAdapter.notifyDataSetChanged();
			}
		}
	
	// TODO: Implement this method
		super.onActivityResult(requestCode, resultCode, data);
	}
/*
	class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			return lines.size();
		}

		@Override
		public Object getItem(int p1)
		{
			return lines.get(p1);
		}

		@Override
		public long getItemId(int p1)
		{
			return getItem(p1).hashCode();
		}

		@Override
		public View getView(int index, View currentView, ViewGroup parentView)
		{
			RowItem rowItem = lines.get(index);
			View result = currentView;
			if (result == null)
			{
				result = getLayoutInflater().inflate(R.layout.listrow, parentView, false);
			}

			TextView typeView = (TextView) result.findViewById(R.id.rowType);
			TextView itemView = (TextView) result.findViewById(R.id.line);
			typeView.setText(rowItem.type);
			itemView.setText(rowItem.line);
			return result;
		}


	}
	*/
}

