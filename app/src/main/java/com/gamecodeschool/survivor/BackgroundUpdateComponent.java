package com.gamecodeschool.survivor;

public class BackgroundUpdateComponent implements UpdateComponent {
    @Override
    public void update(long fps, Transform t, Transform playerTransform) {
        BackgroundTransform bt = ( BackgroundTransform) t;
        PlayerTransform pt = (PlayerTransform) playerTransform;
        float currentXClip = bt.getXClip();



        if(playerTransform.getFacingRight() && !playerTransform.stopingFlag()){
            currentXClip -= t.getSpeed() / fps;
            bt.setXClip(currentXClip);
        }
        else if (!playerTransform.stopingFlag()){
            currentXClip += t.getSpeed() / fps;
            bt.setXClip(currentXClip);
        }



        if( currentXClip >= t.getSize().x){
            bt.setXClip(0);
            bt.flipReversedFirst();
        }
        else  if( currentXClip <= 0){
            bt.setXClip((int) t.getSize().x);
            bt.flipReversedFirst();
        }
    }
}
