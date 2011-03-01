package com.alpha.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import com.alpha.commun.Param;
import com.alpha.commun.ReadSLC;
import com.alpha.model.SLCFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BookView extends Activity {

	private static final int CodeX = 1735;
	private List<SLCFile> listSLC;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.book);

		List<File> listRep = scanFolder(Param.FOLDER_SRC);
		listSLC = new ArrayList<SLCFile>();
		String filename;
		for (File file : listRep) {
			filename = file.getName().toLowerCase();
			if (filename.endsWith(".slc") || filename.endsWith(".slcz")) {
				listSLC.add(new SLCFile(file));
			}
		}
		GridView g = (GridView) findViewById(R.id.books);
		g.setAdapter(new BookAdapter(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(R.string.download);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			Toast.makeText(this, "Check SLC File - Download in progress...",
					Toast.LENGTH_SHORT).show();
			this.DownloadFromUrl(this, Param.HTTP_SRC + Param.FILE_DOWNLOAD,
					Param.FOLDER_SRC + Param.FILE_DOWNLOAD);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void DownloadFromUrl(Context context, String sourceURL,
			String fileName) { // this is the downloader method
		try {
			URL url = new URL(sourceURL); 
			File file = new File(fileName);
			URLConnection ucon = url.openConnection();
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(512);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baf.toByteArray());
			fos.close();

			// FIXME : a corriger !
			/*
			 * Intent intent = new Intent(context, ReadSLC.class); Bundle bundle
			 * = new Bundle(); bundle.putString("source",
			 * FOLDER_SRC+FILE_DOWNLOAD ); if (bundle.containsKey("source")) {
			 * intent.putExtras(bundle); startActivityForResult(intent,CodeX); }
			 */

		} catch (IOException e) {
			Toast.makeText(context, R.string.no_connexion, Toast.LENGTH_LONG)
					.show();
		}
		// FIXME : a supprimer
		Intent intent = new Intent(context, ReadSLC.class);
		Bundle bundle = new Bundle();
		bundle.putString("source", Param.FOLDER_SRC + Param.FILE_DOWNLOAD);
		if (bundle.containsKey("source")) {
			intent.putExtras(bundle);
			startActivityForResult(intent, CodeX);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == CodeX && resultCode == RESULT_CANCELED
				&& "!".equals(data.getAction())) {
			Toast toast = Toast.makeText(this, R.string.file_not_found,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	private List<File> scanFolder(String folder) {
		List<File> files = new ArrayList<File>();
		File ff = new File(folder);

		if (!ff.isDirectory()) {
			ff.mkdirs();
		}

		if (ff.isDirectory()) {
			File[] listFile = ff.listFiles();
			for (File filename : listFile) {
				files.add(filename);
			}
		}
		return files;
	}

	private class BookAdapter extends BaseAdapter {
		private Context mContext;

		public BookAdapter(Context context) {
			mContext = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listSLC.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			BookDetail book;
			if (convertView == null) {
				book = new BookDetail(mContext, listSLC.get(position)
						.getTitre(), listSLC.get(position).isActif());
				book.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						Intent intent = new Intent(mContext, ReadSLC.class);
						Bundle bundle = new Bundle();
						bundle.putString("source", listSLC.get(position)
								.getFilename());
						if (bundle.containsKey("source")) {
							intent.putExtras(bundle);
							startActivityForResult(intent, CodeX);
						}
					}
				});
			} else {
				book = (BookDetail) convertView;
				book.setTitreAndImage(listSLC.get(position));
			}
			return book;
		}

	}

	
	/**
	 * @author Selim A.
	 * create a bookView
	 */
	private class BookDetail extends LinearLayout {
		public BookDetail(Context context, String titre, int img) {
			super(context);
			this.setOrientation(VERTICAL);
			this.setMinimumHeight(40);
			this.setVerticalGravity(Gravity.CENTER_VERTICAL);
			mTitre = new TextView(context);
			mTitre.setText(titre);
			mTitre.setTextColor(0xCCFFFFFF);
			mTitre.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT));
			mTitre.setGravity(Gravity.CENTER_VERTICAL);
			mImg = new ImageView(context);
			mImg.setImageResource(img == 0 ? R.drawable.book_dis
					: R.drawable.book_ena);
			mImg.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.FILL_PARENT));
			addView(mImg, new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			addView(mTitre, new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		}

		public void setTitreAndImage(SLCFile slc) {
			mTitre.setText(slc.getTitre());
			mImg.setImageResource(slc.isActif() == 0 ? R.drawable.book_dis
					: R.drawable.book_ena);
		}

		private ImageView mImg;
		private TextView mTitre;
	}

}
