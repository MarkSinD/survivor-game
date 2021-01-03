package com.gamecodeschool.survivor;

public class InanimateBlockUpdateComponent implements UpdateComponent {
    private boolean mColliderNotSet = true;
    @Override
    public void update(long fps, Transform t, Transform playerTransform) {
        if(mColliderNotSet){
            t.updateCollider();
            mColliderNotSet = false;
        }

    }
}
