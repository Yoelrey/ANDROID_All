package com.example.a54fragmentosexamen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    boolean mDualPane; // Variable para determinar si el diseño es de doble panel o no

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MasterFragment masterFragment = null; // Declaración de un fragmento maestro

        FrameLayout frameLayout = findViewById(R.id.frameLayout);

        if (frameLayout != null) {
            // Si hay un FrameLayout presente, significa que estamos en un diseño de un solo panel
            mDualPane = false;

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Buscar el fragmento maestro por su etiqueta
            masterFragment = (MasterFragment) getSupportFragmentManager()
                    .findFragmentByTag("MASTER");

            // Si no se encuentra, crear uno nuevo y agregarlo al contenedor
            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayout, masterFragment, "MASTER");
            }

            // Eliminar el fragmento de detalle si existe
            DetailFragment detailFragment = (DetailFragment)
                    getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment != null) {
                fragmentTransaction.remove(detailFragment);
            }

            fragmentTransaction.commit();
        } else {
            // Si no hay FrameLayout presente, estamos en un diseño de doble panel
            mDualPane = true;

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Buscar o crear y agregar el fragmento maestro
            masterFragment = (MasterFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frameLayoutMaster);
            if (masterFragment == null) {
                masterFragment = new MasterFragment();
                fragmentTransaction.add(R.id.frameLayoutMaster, masterFragment);
            }

            // Buscar o crear y agregar el fragmento de detalle
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.frameLayoutDetail);
            if (detailFragment == null) {
                detailFragment = new DetailFragment();
                fragmentTransaction.add(R.id.frameLayoutDetail, detailFragment);
            }

            fragmentTransaction.commit();
        }

        // Establecer un listener para manejar eventos de selección en el fragmento maestro
        masterFragment.setOnMasterSelectedListener(new MasterFragment.OnMasterSelectedListener() {
            @Override
            public void onItemSelected(String countryName) {
                // Cuando se selecciona un elemento en el fragmento maestro, llamar a la función para mostrar el detalle
                sendCountryName(countryName);
            }
        });
    }

    // Función para enviar el nombre del país seleccionado al fragmento de detalle
    private void sendCountryName(String countryName) {
        DetailFragment detailFragment;

        if (mDualPane) {
            // Si estamos en un diseño de doble panel, buscar el fragmento de detalle por su ID y mostrar el país seleccionado
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            detailFragment.showSelectedCountry(countryName);
        } else {
            // Si estamos en un diseño de un solo panel, crear un nuevo fragmento de detalle y pasar el nombre del país como argumento
            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailFragment.KEY_COUNTRY_NAME, countryName);
            detailFragment.setArguments(bundle);

            // Reemplazar el fragmento actual en el contenedor y agregar la transacción al historial
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, detailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
