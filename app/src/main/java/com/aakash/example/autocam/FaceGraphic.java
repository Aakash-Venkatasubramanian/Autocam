package com.aakash.example.autocam;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.aakash.example.autocam.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.face.Face;

class FaceGraphic extends GraphicOverlay.Graphic {
    private static final float ID_TEXT_SIZE = 60.0f;
    private static final float BOX_STROKE_WIDTH = 5.0f;
    private static final int VALID_COLOR = Color.GREEN;
    private static final int INVALID_COLOR = Color.RED;
    private Paint mPaint;
    private volatile Face mFace;

    FaceGraphic(GraphicOverlay overlay) {
        super(overlay);
        mPaint = new Paint();
        mPaint.setColor(INVALID_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        mPaint.setTextSize(ID_TEXT_SIZE);
    }

    void setId(int id) {
    }

    void setIsReady(boolean isValid) {
        mPaint.setColor(isValid ? VALID_COLOR : INVALID_COLOR);
    }

    void updateFace(Face face) {
        mFace = face;
        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        Face face = mFace;
        if (face == null) {
            return;
        }
        float x = translateX(face.getPosition().x + face.getWidth() / 2);
        float y = translateY(face.getPosition().y + face.getHeight() / 2);
        float xOffset = scaleX(face.getWidth() / 2.0f);
        float yOffset = scaleY(face.getHeight() / 2.0f);
        float left = x - xOffset;
        float top = y - yOffset;
        float right = x + xOffset;
        float bottom = y + yOffset;
        canvas.drawRect(left, top, right, bottom, mPaint);
    }
}
