package com.ytempest.baselibrary.base.mvp;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class BaseModel implements IModel {

    private IContract mContract;

    @Override
    public <T extends IContract> void setContract(T contract) {
        this.mContract = contract;
    }

    @Override
    public <T extends IContract> T getContract() {
        return (T) mContract;
    }

    @Override
    public void detach() {
        mContract = null;
    }
}
