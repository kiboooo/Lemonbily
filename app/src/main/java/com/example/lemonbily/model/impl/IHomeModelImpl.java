package com.example.lemonbily.model.impl;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.basemodule.bean.JsonResponse;
import com.example.basemodule.bean.UIBeans;
import com.example.basemodule.bean.Video;
import com.example.basemodule.model.BaseModel;
import com.example.basemodule.utils.CommonUtils;
import com.example.lemonbily.R;
import com.example.lemonbily.model.IHomeModel;
import com.example.lemonbily.model.adapter.BannerAdapter;
import com.example.lemonbily.model.adapter.HomeAdapter;
import com.example.lemonbily.model.adapter.onRecyclerViewItemClickListener;
import com.example.lemonbily.model.bean.HomeUIBeans;
import com.example.lemonbily.model.bean.MainData;
import com.example.lemonbily.model.viewHolder.FourViewHolder;
import com.example.lemonbily.model.viewHolder.OneViewHolder;
import com.example.lemonbily.model.viewHolder.TwoViewHolder;
import com.example.lemonbily.presenter.impl.HomePresenter;
import com.example.videoplaymodule.bus.generated.im.EventsDefineAsVideoEvents;
import com.jeremyliao.im.core.InvokingMessage;

import java.util.ArrayList;
import java.util.List;

public class IHomeModelImpl extends BaseModel<HomePresenter>
        implements IHomeModel, onRecyclerViewItemClickListener {

    private List<UIBeans> uiBeansList = new ArrayList<>();
    private HomeAdapter homeAdapter;

    public IHomeModelImpl() {
    }

    public IHomeModelImpl(HomePresenter presenter) {
        super(presenter);
    }

    @Override
    public void initObservers(LifecycleOwner owner) {
        registerInitHomeDataObserver(owner);
    }

    @Override
    public void initErrorObservers(LifecycleOwner owner) {
        registerVideoErrorBusObserver(owner);
    }

    public HomeAdapter produceAdapter(Context context) {
        homeAdapter = new HomeAdapter(context, uiBeansList);
        homeAdapter.addFootView();
        homeAdapter.setItemClickListener(this);
        return homeAdapter;
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, View v, int position) {
        int id = v.getId();
        if (vh instanceof OneViewHolder) {
            switch (id) {
                case R.id.one_item_title:
                    getPresenter().showToast("onClick OneViewHolder title", Toast.LENGTH_SHORT);
                    break;
                case R.id.one_item_image_view:
                case R.id.one_main_content:
                case R.id.one_introduction_content:
                    getPresenter().showToast("onClick to detailed page", Toast.LENGTH_SHORT);
                    break;
                default:
                    break;
            }
        }
        if (vh instanceof TwoViewHolder) {
            switch (id) {
                case R.id.tow_item_title:
                    getPresenter().showToast("onClick TwoViewHolder title", Toast.LENGTH_SHORT);
                    break;

                case R.id.two_onecard_main_content:
                case R.id.two_onecard_introduction_content:
                case R.id.tow_onecard_image_view:
                    getPresenter().showToast("onClick 111 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.two_twocard_main_content:
                case R.id.two_twocard_introduction_content:
                case R.id.tow_twocard_image_view:
                    getPresenter().showToast("onClick 222 to detailed page", Toast.LENGTH_SHORT);
                    break;

                default:
                    break;
            }
        }

        if (vh instanceof FourViewHolder) {
            switch (id) {
                case R.id.four_item_title:
                    getPresenter().showToast("onClick FourViewHolder title", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_onecard_main_content:
                case R.id.four_onecard_introduction_content:
                case R.id.four_onecard_image_view:
                    getPresenter().showToast("onClick 111 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_twocard_main_content:
                case R.id.four_twocard_introduction_content:
                case R.id.four_twocard_image_view:
                    getPresenter().showToast("onClick 222 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_threecard_main_content:
                case R.id.four_threecard_introduction_content:
                case R.id.four_threecard_image_view:
                    getPresenter().showToast("onClick 333 to detailed page", Toast.LENGTH_SHORT);
                    break;

                case R.id.four_fourcard_main_content:
                case R.id.four_fourcard_introduction_content:
                case R.id.four_fourcard_image_view:
                    getPresenter().showToast("onClick 444 to detailed page", Toast.LENGTH_SHORT);
                    break;

                default:
                    if (uiBeansList != null && uiBeansList.size() > 0) {
                        getPresenter().showToast("viewType is "
                                + uiBeansList.get(position).getUiType(), Toast.LENGTH_SHORT);
                    }
                    break;
            }
        }
        if (vh instanceof BannerAdapter.BannerItemViewHolder) {
            getPresenter().showToast(CommonUtils.initBannerList().get(position), Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void onLongItemClick(RecyclerView.ViewHolder vh, View v, int position) {

    }

    private void produceHomePageData() {
        if (MainData.allVideoDataList != null) {
            uiBeansList.clear();
            produceBanner(MainData.allVideoDataList);
            produceOther(MainData.allVideoDataList);
        }
    }

    private void produceBanner(List<Video> videos) {
        if (videos == null || videos.size() == 0) {
            return;
        }
        uiBeansList.add(new UIBeans(HomeUIBeans.BANNER_VIEW, videos));
    }

    private void produceOther(List<Video> videos) {
        if (videos == null || videos.size() == 0) {
            return;
        }
        int max = videos.size();
        for (int i = max - 1; i >= 0; i--) {
            if (i >= 4) {
                producerFourItem(producerDataFactory(videos, 4));
            }else{
                if (i % 2 == 0) {
                    producerTwoItem(producerDataFactory(videos, 2));
                }else {
                    producerOneItem(producerDataFactory(videos, 1));
                }
            }
        }
    }

    private void producerOneItem(List<Video> list) {
        uiBeansList.add(new UIBeans(HomeUIBeans.NORMAL_VIEW, "独家推荐",list.get(0)));
    }

    private void producerTwoItem(List<Video> list) {
        if (list.size() == 2) {
            uiBeansList.add(new UIBeans(HomeUIBeans.TOW_IN_LINE_VIEW,"热门视频", list));
        }
    }

    private void producerFourItem(List<Video> list) {
        if (list.size() == 4) {
            uiBeansList.add(new UIBeans(HomeUIBeans.FOUR_GRID_VIEW,"猜你喜欢", list));
        }
    }

    private List<Video> producerDataFactory(List<Video> list, int lengh) {
        int max = list.size();
        List<Video> datas = new ArrayList<>();
        for (int i = 0; i < lengh; i++) {
            datas.add(list.get((int)( Math.random() * max)));
        }
        return datas;
    }

    private void registerInitHomeDataObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsVideoEvents.class)
                .LOAD_ALL_VIDEO_DATA()
                .observe(owner, new Observer<JsonResponse>() {
                    @Override
                    public void onChanged(@Nullable JsonResponse jsonResponse) {
                        if (null == jsonResponse) {
                            getPresenter().sendErrorMsg("获取Video出错，请稍后重试",
                                    Toast.LENGTH_SHORT);
                        }else {
                            if (jsonResponse.getCode() == 0 ) {
                                MainData.allVideoDataList = (List<Video>) jsonResponse.getData();
                                produceHomePageData();
                                getPresenter().initHomeDataSuccess();
                            } else {
                                getPresenter().sendErrorMsg(jsonResponse.getCode()+" : "
                                        +jsonResponse.getMsg(),Toast.LENGTH_SHORT);
                                getPresenter().initHomeDataFail();
                            }
                        }

                    }
                });
    }

    private void registerVideoErrorBusObserver(LifecycleOwner owner) {
        InvokingMessage.get().as(EventsDefineAsVideoEvents.class)
                .VIDEO_REQUEST_ERROR()
                .observe(owner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        if (getPresenter() != null) {
                            getPresenter().sendErrorMsg(s, Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
}
