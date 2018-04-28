package com.example.vilmarferreira.triagem_avc;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class TelaPrincipal extends AppCompatActivity {

    ImageView vrImage;
    TextView vrText;
    String user;

    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        vrImage=(ImageView) findViewById(R.id.ImagUser);
        vrText=(TextView)findViewById(R.id.textPerfil);

        //carregaPerfil();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tela_principal, container, false);

            if(getArguments().getInt(ARG_SECTION_NUMBER) ==1)
            {}//textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            else{
                if (getArguments().getInt(ARG_SECTION_NUMBER)==2)
                {
                    rootView = inflater.inflate(R.layout.fragment_fragment_tab_2, container, false);
                }
                else {
                    if (getArguments().getInt(ARG_SECTION_NUMBER)==3)
                    {
                        rootView = inflater.inflate(R.layout.activity_maps, container, false);
                    }
                    else
                    {
                        if (getArguments().getInt(ARG_SECTION_NUMBER)==4)
                        {
                            DownloadConteudo downloadConteudo = new DownloadConteudo ();

                            // ActivityCompat.requestPermissions(this, new String [](android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_NETWORK_STATE), 12);
                            try
                            {
                                downloadConteudo.execute(new URL("http://ea445c14.ngrok.io/postagens"));
                            }
                            catch (Exception e)
                            {

                            }
                            rootView = inflater.inflate(R.layout.fragment_listar, container, false);
                        }

                    }
                }
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }
    public void onClickBatePapo (View v)
    {
        Intent intent = new Intent(this, chatActivity.class);
        startActivity(intent);
    }

    public void carregaPerfil ()
    {
        String a=getIntent().getExtras().getString("Senha");
        user = getIntent().getExtras().getString("login");
        if (user.equals("Vilmar"))
        {
            vrImage.setImageResource(R.mipmap.img_user);
            vrText.setText("Seja bem Vindo: "+user);
        }
        else
        {
            if(user.equals("admin"))
            {
                vrImage.setImageResource(R.mipmap.img_user2);
                vrText.setText("Seja bem Vindo: "+user);
            }
        }
    }
    public void onActivityResult(int codTela, int result, Intent dados)
    {
        if(result== Activity.RESULT_CANCELED)
        {
            return;
        }
        if(codTela==2)
        {
            Bundle bundle= dados.getExtras();
            String login=bundle.getString("login");

        }
    }
}
