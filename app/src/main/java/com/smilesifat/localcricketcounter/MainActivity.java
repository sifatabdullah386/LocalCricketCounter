package com.smilesifat.localcricketcounter;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling toolbar actionbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //calling drawer menu
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(drawerNavListener);

        //calling drawer layout
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
        toggle.syncState();

        //added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeActivity()).commit();
        }
    }

    //for Navigation Drawer menu item multiple selection
    private NavigationView.OnNavigationItemSelectedListener drawerNavListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item){
            switch (item.getItemId()) {
                case R.id.drawer_new_match:
                    Intent intent = new Intent(MainActivity.this,MatchDetailsActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"New Match",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_save_matches:
                    Intent intent1 = new Intent(MainActivity.this,MatchesListActivity.class);
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(),"Saved matches",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_my_profile:
                    setContentView(R.layout.my_profile);
                    Toast.makeText(getApplicationContext(),"Update Matches",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_share:
                    Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_rate_us:
                    Toast.makeText(getApplicationContext(),"Rate US",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_about_the_apps:
                    setContentView(R.layout.about_the_app);
                    Toast.makeText(getApplicationContext(),"About the Developers",Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
            return true;
        }

    };

    //for navigation drawer backpressed
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    //for toolbar 3dot icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settings_menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //for settings menu items multiple selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_notification_settings:
                Toast.makeText(getApplicationContext(),"Notification Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_share:
                Toast.makeText(getApplicationContext(),"Share Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_rate_us:
                Toast.makeText(getApplicationContext(),"Rate Us Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_exit:
                Toast.makeText(getApplicationContext(),"Exit Settings",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
