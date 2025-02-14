package com.v5foradnroid.userapp.fragments;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.v5foradnroid.userapp.Config;
import com.v5foradnroid.userapp.R;
import com.v5foradnroid.userapp.activities.ActivityHelp;
import com.v5foradnroid.userapp.activities.MyApplication;
import com.v5foradnroid.userapp.adapters.RecyclerAdapterHelp;
import com.v5foradnroid.userapp.models.Help;
import com.v5foradnroid.userapp.utilities.MyDividerItemDecoration;
import com.v5foradnroid.userapp.utilities.Utils;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class FragmentHelp extends Fragment implements RecyclerAdapterHelp.ContactsAdapterListener {

    String var_twoe;
    private RecyclerView recyclerView;
    private List<Help> helpList;
    private RecyclerAdapterHelp mAdapter;
    private SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout = null;
    LinearLayout lyt_root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);
        setHasOptionsMenu(true);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        lyt_root = view.findViewById(R.id.lyt_root);
        if (Config.ENABLE_RTL_MODE) {
            lyt_root.setRotationY(180);
        }

        var_twoe = getPreftwoe("twoe", requireContext());
        recyclerView = view.findViewById(R.id.recycler_view);
        helpList = new ArrayList<>();
        mAdapter = new RecyclerAdapterHelp(getActivity(), helpList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0));
        recyclerView.setAdapter(mAdapter);

        fetchContacts();
        onRefresh();

        return view;
    }

    private void onRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                helpList.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (Utils.isNetworkAvailable(getActivity())) {
                            swipeRefreshLayout.setRefreshing(false);
                            fetchContacts();
                        } else {
                            swipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(getActivity(), getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, 1500);
            }
        });
    }

    private void fetchContacts() {
        JsonArrayRequest request = new JsonArrayRequest(var_twoe + "/api/api.php?get_help", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response == null) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.failed_fetch_data), Toast.LENGTH_LONG).show();
                    return;
                }

                List<Help> items = new Gson().fromJson(response.toString(), new TypeToken<List<Help>>() {
                }.getType());
                // adding contacts to contacts list
                helpList.clear();
                helpList.addAll(items);
                // refreshing recycler view
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (NullPointerException e) {

                }
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onContactSelected(Help help) {
        Intent intent = new Intent(getActivity(), ActivityHelp.class);
        intent.putExtra("title", help.getTitle());
        intent.putExtra("content", help.getContent());
        startActivity(intent);
    }

    public static String getPreftwoe(String str, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, "never");
    }

}