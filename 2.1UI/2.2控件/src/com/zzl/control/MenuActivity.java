package com.zzl.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	setContentView(R.layout.menuview);
	super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	menu.add(0, 0, Menu.NONE, "�˵�1").setIcon(R.drawable.icon);
	menu.add(0, 1, Menu.NONE, "�˵�2").setIcon(R.drawable.icon);
	menu.add(0, 2, Menu.NONE, "�˵�3").setIcon(R.drawable.icon);
	menu.add(0, 3, Menu.NONE, "�˵�4").setIcon(R.drawable.icon);
	menu.add(0, 4, Menu.NONE, "�˵�5").setIcon(R.drawable.icon);
	menu.add(0, 5, Menu.NONE, "�˵�6").setIcon(R.drawable.icon);
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	Dialog(item.getItemId());
	return super.onOptionsItemSelected(item);
    }

    private void Dialog(int message) {
	new AlertDialog.Builder(this).setMessage(
		"�������ڡ�" + message + "����Menu�˵���.").show();
    }
}
