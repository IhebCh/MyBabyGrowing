package com.itech.mybabygrowing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.doomonafireball.betterpickers.numberpicker.NumberPickerBuilder;
import com.doomonafireball.betterpickers.numberpicker.NumberPickerDialogFragment;


public class ProjectNaissanceActivity extends ActionBarActivity implements NumberPickerDialogFragment.NumberPickerDialogHandler  {

    EditText nom,prenom ;
    TextView baby_age ;
    RadioGroup sexe  ;

    public void ajouterAgeBebe(View v){


                NumberPickerBuilder npb = new NumberPickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment);

                npb.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_naissance);

        baby_age=(TextView)findViewById(R.id.baby_age);
        nom=(EditText)findViewById(R.id.nom);
        prenom=(EditText)findViewById(R.id.prenom);
        sexe=(RadioGroup)findViewById(R.id.sexe);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_naissance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void creerProjetNaissance(View v){

        ProjetNaissance projetNaissance = new ProjetNaissance() ;

        projetNaissance.setDate(baby_age.getText().toString());
        projetNaissance.setNom(nom.getText().toString());
        projetNaissance.setPrenom(prenom.getText().toString());

        int selectedId=sexe.getCheckedRadioButtonId();

        if(selectedId==R.id.radioM) projetNaissance.setIsMale(true);
        else projetNaissance.setIsMale(false);

        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void onDialogNumberSet(int i, int i1, double v, boolean b, double v1) {

        baby_age.setText(v1+" Semaine(s)");
    }
}
