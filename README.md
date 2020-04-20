# Demostração de como fazer uma animação usando constraintSet

Essa é apenas uma demostração do poderoso ConstraintSet

![enter image description here](https://raw.githubusercontent.com/GaldinoJr/TestConstraintSet/master/app/src/main/res/raw/demo.gif)
# Layout utilizado


```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
  android:id="@+id/clRoot"
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".MainActivity">

 <androidx.constraintlayout.widget.ConstraintLayout  android:id="@+id/clGokStatus"
  android:layout_width="0dp"
  android:layout_height="wrap_content"
  android:background="#99000000"
  app:layout_constraintEnd_toEndOf="parent"
  app:layout_constraintStart_toStartOf="parent"
  app:layout_constraintTop_toTopOf="parent">

 <TextView  android:id="@+id/tvGokuStatus"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:text="@string/goku_is_here"
  app:layout_constraintLeft_toLeftOf="parent"
  app:layout_constraintRight_toRightOf="parent"
  app:layout_constraintTop_toTopOf="parent" />

 <ImageView  android:id="@+id/ivGoku"
  android:layout_width="0dp"
  android:layout_height="300dp"
  app:layout_constraintTop_toBottomOf="@+id/tvGokuStatus"
  app:layout_constraintStart_toStartOf="parent"
  app:layout_constraintEnd_toEndOf="parent"
  android:src="@drawable/goku_teletransport"
  android:scaleType="centerInside"
  />

 </androidx.constraintlayout.widget.ConstraintLayout>
 <Button  android:id="@+id/btChangeStatus"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  app:layout_constraintTop_toBottomOf="@+id/clGokStatus"
  app:layout_constraintStart_toStartOf="parent"
  app:layout_constraintEnd_toEndOf="parent"
  android:text="@string/change_status"
  />


</androidx.constraintlayout.widget.ConstraintLayout>
```


# Ao abrir a tela, Clonar o ConstraintLayout pai do objeto que quer animar

Clonar a tela assim que abrir a tela. (Oncreate ou onViewCreated)
Isso fará com que o constraintSet copie os status dos elementos atuais.

**Observação importante 1, É importante notar que é preciso clonar o Constraint layout pai do objeto que quer animar, se não fizer isso, não funciona. notar no xml do projeto, coloquei dois constraintLayouts apenas para exemplificar qual é o correto pegar**

**Observação importante 2, para que funcione corretamente, é preciso que todos os elementos ta tela estejam nomeados**

    mConstraintSet = ConstraintSet()
    mConstraintSet.clone(clGokStatus)

## Animando

Para animar, você tem diversas opções, trocar visibilidade, posição dos objetos, cor e etc, as opções são grandes, esse tutorial apenas troca a visibilidade e um texto, para demostrar como a tela se comporta.

Sendo assim, mostramos a imagem, quando a mesma estiver escondida, e escondemos quando a mesma estiver visível.

     if(isHide){
        tvGokuStatus.setText(R.string.goku_is_not_here)
        mConstraintSet.setVisibility(ivGoku.id, ConstraintSet.GONE)
    }
    else{
        tvGokuStatus.setText(R.string.goku_is_here)
        mConstraintSet.setVisibility(ivGoku.id, ConstraintSet.VISIBLE)
    }
    applyAnimation()


## Aplicar a animação

Por ultimo, mas não menos importante, basta aplicar a animação ao layout pai do objeto

    private fun applyAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(clGokStatus)
        }
        mConstraintSet.applyTo(clGokStatus)
    }
