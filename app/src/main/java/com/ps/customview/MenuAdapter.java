package com.ps.customview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by PengSong on 17/12/7.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context mContext;
    private List<MenuEntity> menuEntities;

    public MenuAdapter(Context mContext,List<MenuEntity> menuEntities) {
        this.mContext = mContext;
        this.menuEntities = menuEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_rv_menu,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MenuEntity entity = menuEntities.get(position);
        holder.btnMenu.setText(entity.getMenuName());
        holder.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,entity.getClassName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuEntities != null ? menuEntities.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        Button btnMenu;
        public ViewHolder(View itemView) {
            super(itemView);
            btnMenu = itemView.findViewById(R.id.btn_menu);
        }
    }
}
