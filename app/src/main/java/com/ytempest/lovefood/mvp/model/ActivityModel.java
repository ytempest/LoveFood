package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.ActivityInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.ActivityContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class ActivityModel extends BaseModel implements ActivityContract.Model {
    @Override
    public Observable<BaseResult<DataList<ActivityInfo>>> getActivityList(int pageNum, int pageSize) {
        // FIXME heqidu: 2019/3/6

//        return new Observable<BaseResult<DataList<ActivityInfo>>>() {
//            @Override
//            protected void subscribeActual(Observer<? super BaseResult<DataList<ActivityInfo>>> observer) {
//                try {
//                    Thread.sleep(2500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                DataList<ActivityInfo> data = new DataList<>();
//                data.setTotal(15);
//                List<ActivityInfo> list = new ArrayList<>();
//                for (int i = 0; i < 5; i++) {
//                    ActivityInfo info = new ActivityInfo();
//                    info.setActId((long) i);
//                    info.setActTitle("标题" + i);
//                    info.setActDesc("内容" + i);
//                    info.setActStartTime(TimeUtils.getTodayStart());
//                    info.setActFinishTime(System.currentTimeMillis());
//                    info.setActImageUrl("https://t2.hddhhn.com/uploads/tu/201610/198/hkgip2b102z.jpg");
//                    list.add(info);
//                }
//                data.setList(list);
//                BaseResult<DataList<ActivityInfo>> result = new BaseResult<>();
//                result.setCode(1);
//                result.setMsg("msg");
//                result.setData(data);
//                observer.onNext(result);
//            }
//        }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
        return RetrofitClient.client().getService().getActivityList(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
