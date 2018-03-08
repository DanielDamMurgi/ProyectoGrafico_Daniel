package com.example.usuario2.proyectografico_daniel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Vista(this));

    }

    public class Vista extends View{

        DatosGrafica datosGrafica= new DatosGrafica();


        private Paint lineaX, lineaY, barras;
        private int espacioBarras=20, cantLineas=7;
        private int width;
        private int height;
        private int cant=18000,suma;
        private int tamañoYGrafico;
        private int paddingBottom;
        private int paddingBottomLeyenda, paddingLeftLeyenda, espacioLeyenda;

        public Vista(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);


            paddingBottom=height-100;
            paddingLeftLeyenda=20;
            paddingBottomLeyenda=height-20;

            tamañoYGrafico=paddingBottom;
            tamañoYGrafico=tamañoYGrafico-40;

            lineaY = new Paint(Paint.ANTI_ALIAS_FLAG);
            lineaX = new Paint(Paint.ANTI_ALIAS_FLAG);
            barras = new Paint(Paint.ANTI_ALIAS_FLAG);
            Paint texto = new Paint();

            lineaX.setColor(getResources().getColor(R.color.grafico));
            lineaX.setStrokeWidth(3);

            lineaX.setStyle(Paint.Style.STROKE);

            canvas.drawLine(60, paddingBottom, width-20, paddingBottom, lineaX);

            lineaY.setColor(getResources().getColor(R.color.grafico));
            lineaY.setStrokeWidth(3);
            lineaY.setStyle(Paint.Style.STROKE);

            //canvas.drawLine(60, 20, 60, height-40, lineaY);

            texto.setColor(Color.BLACK);
            texto.setTextSize(30);

            barras.setColor(getResources().getColor(R.color.companyB));

            int left=150;
            int top=70;
            int anchoBarra=calcularAnchoBarras(150,width-20, datosGrafica.getBarras().size());
            int right=anchoBarra+left;
            int bottom=paddingBottom;

            int barrasX=calcularBarrasX(cantLineas);
            int t=paddingBottom;

            int valor=0;

            suma=cant/(cantLineas-1);

            //bucle que pinta las lineas de valor
            for (int i=0;i<cantLineas;i++){

                canvas.drawText(valor+" €", 30,t-7, texto);
                canvas.drawLine(60,t,width-20,t,lineaX);

                valor=valor+suma;
                t=t-barrasX;

            }
            datosGrafica.touch.clear();
            //bucle que pinta las barras de la gráfica
            for (int i=0;i<datosGrafica.getBarras().size();i++){
                barras.setColor(getResources().getColor(datosGrafica.getBarras().get(i).getEmpresa().getColor()));

                canvas.drawRect(left,valorBarras(datosGrafica.getBarras().get(i).getValor()),right,bottom,barras);



                datosGrafica.touch.add(new Touch(left,right,bottom,valorBarras(datosGrafica.getBarras().get(i).getValor()),datosGrafica.getBarras().get(i).getValor()));
                left=right+espacioBarras;
                right=left+anchoBarra;

            }

            int leftLeyenda=paddingLeftLeyenda;
            int anchoLeyenda=pintarLeyenda(datosGrafica.getEmpresa().size());

            for (int i=0;i<datosGrafica.getEmpresa().size();i++) {
                barras.setColor(getResources().getColor(datosGrafica.getBarras().get(i).getEmpresa().getColor()));
                texto.setTextSize(40);
                canvas.drawRect(leftLeyenda, paddingBottomLeyenda-30,leftLeyenda+30,paddingBottomLeyenda,barras);
                canvas.drawText(datosGrafica.getBarras().get(i).getEmpresa().getNombre(),leftLeyenda+40,paddingBottomLeyenda-3,texto);

                leftLeyenda=leftLeyenda+anchoLeyenda;

            }
        }

        private float mPreviousX;
        private float mPreviousY;



        @Override
        public boolean onTouchEvent(MotionEvent e) {

            float x = e.getX();
            float y = e.getY();

            switch (e.getAction()){
                case MotionEvent.ACTION_UP:

                    for (int i=0;i<datosGrafica.getTouch().size();i++){
                        if(x>=datosGrafica.getTouch().get(i).getInicioX() && x<=datosGrafica.getTouch().get(i).getFinX()){
                            if (y<=datosGrafica.getTouch().get(i).getInicioY() && y>=datosGrafica.getTouch().get(i).getFinY()) {
                                Toast.makeText(getApplicationContext(), String.valueOf(datosGrafica.getTouch().get(i).getValor()), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    break;
            }



            return true;
        }

        //Metodo que calcula el ancho de las barras según la cantidad de barras que hay en el grafico
        private int calcularAnchoBarras(int t, int t2, int cantBarras){
            int tTotal=t2-t;
            tTotal=(tTotal/cantBarras)+(-espacioBarras*cantBarras-1)/cantBarras;
            return tTotal;
        }

        private int pintarLeyenda(int cantEmpresa){
            int tTotal=(width/(cantEmpresa));
            return tTotal;
        }

        //Metodo que calcula la distancia entre las lineas de valor que se pintan en la gráfica
        private int calcularBarrasX(int cantLineas){
            int tTotal=(tamañoYGrafico/(cantLineas-1));
            return tTotal;
        }

        //calcula el largo de las barras segun su valor
        private int valorBarras(int n){

            int num=(n*tamañoYGrafico);
            num=num/18000;

            num=tamañoYGrafico-num;

            num=num+40;

            //Log.v(VIEW_LOG_TAG,""+num);
            return num;
        }



        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            int desiredWidth = 100;
            int desiredHeight = 100;

            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            int width;
            int height;

            //Measure Width
            if (widthMode == MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = Math.min(desiredWidth, widthSize);
            } else {
                //Be whatever you want
                width = desiredWidth;
            }

            //Measure Height
            if (heightMode == MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = Math.min(desiredHeight, heightSize);
            } else {
                //Be whatever you want
                height = desiredHeight;
            }
            this.width=width;

            this.height=height;


            //MUST CALL THIS
            setMeasuredDimension(width, height);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            int pl = getPaddingLeft();
            int pr = getPaddingRight();
            int pt = getPaddingTop();
            int pb = getPaddingBottom();

            int usableWidth = w - (pl + pr);
            int usableHeight = h - (pt + pb);

        }
    }
}
