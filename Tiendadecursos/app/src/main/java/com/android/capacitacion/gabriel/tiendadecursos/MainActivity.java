package com.android.capacitacion.gabriel.tiendadecursos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Se declaran los elementos a usar en la lógica
    Button btnCarrito;
    TextView itemsCarrito;
    ListView listaCursos;
    ArrayList<Curso> cursos;
    Random ranking = new Random(System.currentTimeMillis());
    public static final int DESCRIPCION_ARTICULO = 1;
    public static final int CARRITO_DE_COMPRAS = 2;
    ArrayList<Curso> cursos_a_comprar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se asocia la vista con la lógica
        btnCarrito = findViewById(R.id.btn_irCarrito);
        itemsCarrito = findViewById(R.id.tv_numItemsCarrito);
        listaCursos = findViewById(R.id.listaCursos);
        cursos = new ArrayList<>();
        cursos_a_comprar = new ArrayList<>();
        //Se agregan los cursos
        agregaCursos();


        listaCursos.setAdapter(new CursoAdapter());



        /********************** Se abre una nueva actividad cada que se presiona un Curso ************************/
        listaCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Se crea un intento nuevo
                Intent intento = new Intent(MainActivity.this, DescripcionActivity.class);
                //Se añade la información a compartir con el intento, en este caso un objeto
                intento.putExtra("curso", cursos.get(position));        //Por que serializable la clase?
                //Inicio una actividad con espera de un resultado
                startActivityForResult(intento, DESCRIPCION_ARTICULO);
            }
        });

        /*********************** Se abre una actividad cada que se quiere visualizar el carrito *************************/
        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Se abre el carrito!", Toast.LENGTH_SHORT).show();
                Intent intentoCarrito = new Intent(MainActivity.this, CarritoActivity.class);
                //Se añade la información a compartir con el intento, en este caso el arreglo de Cursos elegidos
                intentoCarrito.putExtra("elegidos", cursos_a_comprar);
                //Inicio una actividad con espera de un resultado
                startActivityForResult(intentoCarrito, CARRITO_DE_COMPRAS);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DESCRIPCION_ARTICULO){
            if (resultCode == RESULT_OK){
                Curso elegido = (Curso) data.getSerializableExtra("agregar_a_carrito");
                cursos_a_comprar.add(elegido);
                //Se incrementa el valor de items en el Carrito con el tamaño del ArrayList
                itemsCarrito.setText(String.valueOf(cursos_a_comprar.size()));
            }
        }
        if (requestCode == CARRITO_DE_COMPRAS){
            if (resultCode == RESULT_OK){
                ArrayList<Curso> cursosActualizadosEnCarrito = (ArrayList<Curso>) data.getSerializableExtra("actualizarCarrito");
                cursos_a_comprar = cursosActualizadosEnCarrito;
                //Se actualiza el valor de items en el Carrito con el tamaño del ArrayList
                itemsCarrito.setText(String.valueOf(cursos_a_comprar.size()));
            }
        }
    }

    class CursoAdapter extends ArrayAdapter<Curso> {

        CursoAdapter(){super(MainActivity.this, R.layout.row_muestra, cursos);}

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = getLayoutInflater().inflate(R.layout.row_muestra, parent, false);//Para que es?

            //Asociamos los elementos visuales con la lógica
            ImageView imagenCurso = row.findViewById(R.id.imagenCurso);
            TextView nombreCurso = row.findViewById(R.id.tv_nombreCurso);
            TextView nombreInstructor = row.findViewById(R.id.tv_nombreInstructor);
            TextView precioCurso = row.findViewById(R.id.tv_precio);
            RatingBar rating = row.findViewById(R.id.rating);

            //
            Curso cursoActual = cursos.get(position);

            imagenCurso.setImageResource(cursoActual.getImagenCurso());
            nombreCurso.setText(cursoActual.getNombreCurso());
            nombreInstructor.setText(cursoActual.getNombreInstructor());
            precioCurso.setText(String.format("$ %1$.2f", cursoActual.getCosto()));
            rating.setRating((float)cursoActual.getPuntuación());


            return row;
        }
    }


    private void agregaCursos() {
        //Agregamos los 22 cursos
        cursos.add(new Curso(R.drawable.curso1, R.drawable.instructor1,
                "Viste con Estilo!",
                "Aaron Arizmendi Guerrero",
                "Aprende a vestirte y transforma tu imagen\n" +
                        "¿Quieres aprender a vestirte como toda una experta?\n" +
                        "¿Y generar un impacto positivo con el buen manejo de tu\n" +
                        "imagen personal en las diferentes áreas de tu vida?\n" +
                        "\n" +
                        "Este curso está diseñado para brindarte las herramientas necesarias para llevar tu imagen personal a un siguiente nivel.",
                "Sin requisitos",
                3_685, generaRanking()));
        cursos.add(new Curso(R.drawable.curso2, R.drawable.instructor2,
                "Hablando frente al público",
                "Adrián Cabrera Flores",
                "El curso de Oratoria te enseña a empoderarte con tu palabra, te ayudamos a dominar el nerviosismo, los temblores, la boca seca y demás vicios de la expresividad verbal y no verbal. En el curso de hablar en publico participas una y otra vez en simulaciones de discursos frente a  muchas personas, cada vez que pasas al frente en el curso de oratoria, te vas sintiendo más suelto y mejor. Queremos que en el curso de oratoria te sientas muy bien cuando te toque pasar al frente a hablar en publico y además te ayude positivamente en tu carrera o trabajo.",
                "Carta Motivos\n" +
                        "Requieres redactar una carta Motivos o intención, en donde nos indiques las metas que quieres alcanzar, es muy importante ya que así el instructor conocerá que quieres lograr y adecuará ejercicios para que lo logres.",
                8535, generaRanking()));
        cursos.add(new Curso(R.drawable.curso3, R.drawable.instructor3,
                "Aprende a tocar Violín",
                "Alberto Dominguez Flores",
                "Hay una máxima muy clara: a los mexicanos les gusta la música. ¿Y a ti? Eso sí, el violín no es uno de los instrumentos preferidos por la población. De hecho, el piano o la guitarra son los que encabezan la lista. No obstante, a pesar de la opinión general, el violín es uno de los instrumentos más accesibles y, de hecho, su precio no es caro en absoluto.",
                "Contar con instrumento",
                4500.00, generaRanking()));
        cursos.add(new Curso(R.drawable.curso4,R.drawable.instructor4,
                "Soldadura y forja",
                "Andrés Munera Rodríguez",
                "¡Bienvenido!\n" +
                        "\n" +
                        "Ser un soldador es importante para varios sectores de la industria, ya que pueden unir y reparar equipos o productos que contienen material metálico. Además, un soldador puede especializarse en la técnica de oxicorte.\n" +
                        "\n" +
                        "En esta capacitación, aprenderás a identificar las técnicas y materiales implementados para realizar las diversas fases de tu trabajo, las medidas de seguridad así como la aplicación de estos conocimientos.\n" +
                        "\n" +
                        "¡Éxito en tu capacitación!",
                "Sin requisitos",
                3463,generaRanking()));
        cursos.add(new Curso(R.drawable.curso5,R.drawable.instructor5,
                "Manejo de Katana",
                "Alma Edith Martínez Licona",
                "¡Bienvenidos al Curso de Katana Tradicional básico en Dragonz.es! En este curso de introducción al manejo del sable japonés, os enseñaré las principales posiciones y cortes, y como combinarlas entre sí.\n" +
                        "\n" +
                        "La katana ha sido considerada desde siempre, como “el alma del guerrero”, y su uso con el paso de los años, fue variando desde el combate, en la época de los samurai, \n" +
                        "hacia el desarrollo técnico y espiritual, una vez aparecieron las armas de fuego y se hizo innecesario su uso, llegándose a prohibir portarla por las calles de Japón.",
                "No se requiere contar con una Katana",
                7835,generaRanking()));
        cursos.add(new Curso(R.drawable.curso6,R.drawable.instructor6,
                "Técnias de Dirección de Orquesta",
                "Carlos Araujo Andrade",
                "\"Si alguna vez has visto a un director de orquesta agitar sus brazos alrededor y pensaste, \"yo podría hacer eso,\" aquí está su oportunidad\". Es una de las frases de bienvenida de Carlos Araujo Andrade, el famoso director de orquesta y titular de la Sinfónica de Detroit, en el primero de los videos de su canal de Youtube y en su web en los que se propone enseñar los secretos de la dirección orquestal, nada menos.  " +
                        "\"Creo que esta serie de videos será educativa, entretenida, divertida y didáctica, concluye el director. La primera lección se espera mañana sábado.",
                "Ninguno",
                2345,generaRanking()));
        cursos.add(new Curso(R.drawable.curso7,R.drawable.instructor7,
                "Probabilidad y Estadísticas",
                "Carlos Campos Rivera",
                "En este curso de Probabilidad y Estadística estudiamos dos áreas fundamentales del conocimiento: La Probabilidad como una rama de las matemáticas que mide cuantitativamente la posibilidad de que un experimento produzca un determinado resultado, y la Estadística como ciencia formal que estudia la recolección, análisis e interpretación de datos de una muestra. Los temas que puedes encontrar a través de nuestras lecciones son: conceptos básicos de estadística, distribuciones unidimensionales ",
                "Algebra Lineal Avanzada",
                4523,generaRanking()));
        cursos.add(new Curso(R.drawable.curso8,R.drawable.instructor8,
                "Base de datos en MySQL",
                "César Enrique Miranda López",
                "Bienvenido!\nUn administrador de base de datos es un experto en el diseño y mantenimiento de las bases de datos relacionales. Su función es muy importante para todo negocio cuyo crecimiento depende del manejo eficiente de la información.",
                "Curso Curador de Datos",
                4325,generaRanking()));
        cursos.add(new Curso(R.drawable.curso9,R.drawable.instructor9,
                "Filosofía compleja",
                "Cristian Rodríguez",
                "Este Curso de Filosofía elemental, del profesor Cristian Rodríguez,\n" +
                        "del Instituto S. Gili Gaya de Lérida, consta de 15 capítulos -que iremos\n" +
                        "instruyendo aquí sucesivamente- en los que se van desarrollando los conceptos\n" +
                        "que todos barajamos -porque todos tenemos, consciente o inconscientemente,\n" +
                        "una filosofía- en torno al mundo, al hombre y a Dios. Es sencillo, claro, al\n" +
                        "alcance de todos. Constituye un instrumento muy útil para contrastar el valor\n" +
                        "de nuestras ideas sobre los temas más trascendentes de la vida humana.",
                "Ganas por aprender",
                5423,generaRanking()));
        cursos.add(new Curso(R.drawable.curso10, R.drawable.instructor10,
                "Sexología para Dummies",
                "Cristina Vargas Puente",
                "Contamos con un área de formación on-line, desde la que ofrecemos cursos de Sexología a través de nuestra plataforma de formación, dirigidos a profesionales y también al público general interesado en ampliar sus conocimientos sobre Sexología.\n" +
                        "Actualmente contamos con los siguientes cursos:\n" +
                        "\n" +
                        "SEXOLOGÍA BÁSICA\n" +
                        "SEXUALIDADES, DISCAPACIDADES Y DIVERSIDADES \n" +
                        "INTERVENCIÓN EN RELACIONES ABUSIVAS",
                "Sin requisitos",
                5600, generaRanking()));
        cursos.add(new Curso(R.drawable.curso11, R.drawable.instructor11,
                "Aprende a tocar Piano",
                "Daniel Gloria Florencio",
                "Este curso de piano para principiantes  es un curso diseñado para que aprendas a tocar por ti mismo y paso a paso. No es necesario tener ningún conocimiento musical previo. También es un curso interesante para músicos de otros instrumentos que se quieren iniciar en el piano o en el teclado. Esto sería como complemento a sus conocimientos musicales  o para enseñar  a alumnos principiantes.",
                "Sin requisitos",
                7643, generaRanking()));
        cursos.add(new Curso(R.drawable.curso12, R.drawable.instructor12,
                "Conviertete en Standupero!",
                "David Gonzalez Alba",
                "Cursos de Monólogos y Comedia de Improvisación\n" +
                        "Próximos cursos:\n" +
                        "\n" +
                        "Curso de Stand Up (Monólogos) Nivel 1 desde el 10 y el 11 de abril de 2018 Inscripción abierta.\n" +
                        "Curso Intensivo de Comedia de Improvisación  el 5 y 6 de mayo Abierto el plazo de inscripción.\n" +
                        "Curso de Stand Up (Monólogos) Nivel 2 desde septiembre de 2018 Inscripción abierta.",
                "Sin requisitos",
                9586, generaRanking()));
        cursos.add(new Curso(R.drawable.curso13, R.drawable.instructor13,
                "Marketing Digital",
                "Edmundo Esteban Delgado López",
                "EL  MÉTODO \n" +
                        "Nuestro metodo de aprendizaje está basado en la metodología inbound, el cual permite que los usuarios y clientes potenciales te encuentren en Internet y conozcan tus productos y servicios. Se trata de ofrecer valor de una forma no intrusiva. Con las técnicas inbound, tus clientes se acercan a ti de manera orgánica y natural.",
                "Sin requisitos",
                5342, generaRanking()));
        cursos.add(new Curso(R.drawable.curso14, R.drawable.instructor14,
                "Dirección de Proyectos",
                "Edwin Vilchis Soriano",
                "CURSO ONLINE PMP® Y CAPM®\n" +
                        "* SOMOS CENTRO R.E.P. DEL PMI®\n" +
                        "\n" +
                        "* OTORGA DIPLOMA 35 CONTACT HOURS\n" +
                        "\n" +
                        "* CURSOS BASADOS EN \"A Guide to the Project Management Body of Knowledge (PMBOK® Guide) – Fifth Edition, Project Management Institute, Inc., 2013; PMBOK® Guide 5  y Sixth Edition, Project Management Institute, Inc., 2017\"; PMBOK® Guide 6  en ESPAÑOL.\n" +
                        "\n" +
                        "* INCLUYE SIMULADOR DE EXAMEN CON MÁS DE 3.000 PREGUNTAS Y MÁS DE 70 VIDEOS DIDÁCTICOS\n" +
                        "\n" +
                        "*PROFESORES CERTIFICADOS",
                "Sin requisitos",
                8356, generaRanking()));
        cursos.add(new Curso(R.drawable.curso15, R.drawable.instructor15,
                "Diseño Gráfico sobre Facebook",
                "Eliana García Ramírez",
                "Curso de Diseño Gráfico Editorial con Adobe CC para Facebook\n" +
                        "Diseñar y producir documentos para impresión de una manera eficiente y con calidad. Sin importar su destino: Alta o baja resolución, salida digital o a Preprensa, trabajando con software de última generación para el control del flujo editorial, que te permita alcanzar tiempos de entrega y demanda, son sólo algunos de los objetivos de este curso.",
                "Sin requisitos",
                8356, generaRanking()));
        cursos.add(new Curso(R.drawable.curso16, R.drawable.instructor16,
                "DiscipliNA-ATe",
                "Erick Rivera Sánchez",
                "¿Quieres aprender nuevas formas de gestionar los problemas de convivencia en tu trabajo?\n" +
                        "El curso “DiscipliNA-ATe” es un programa de 9 semanas de duración -27 lecciones- en el que, con ayuda de las herramientas basadas en la disciplina positiva, aprenderemos nuevas formas de gestionar los problemas de convivencia en nuestros trabajos.\n" +
                        "\n" +
                        "Este curso surgió a consecuencia de las necesidades de los desarrolladores del la consultora NA-AT, que eran muy conscientes de que educar con premios y castigos no era lo más deseable ni coherente con la capacitación que estaban facilitando a sus trabajadores, pero también se veían en ocasiones frustrados, superados y sin recursos.",
                "Sin requisitos",
                9468, generaRanking()));
        cursos.add(new Curso(R.drawable.curso17, R.drawable.instructor17,
                "Aerobics primer nivel",
                "Erick Uriel Vargas Mora",
                "¿Te atrae el mundo del deporte?, ¿Quieres guiar a un grupo de atletas en una clase de aeróbic? Si te gusta hacer ejercicio y quieres poseer los conocimientos sobre el aeróbic para poder enseñarlo en una clase apúntate a este curso. En este curso podrás encontrar éste y otros cursos de formación similares que amplíen tus conocimientos en el deporte aeróbico",
                "Sin requisitos",
                1754, generaRanking()));
        cursos.add(new Curso(R.drawable.curso18, R.drawable.instructor18,
                "Maestr@ de la seducción",
                "Ernesto Salvador Endeje",
                "Este es un gran y completo curso sobre seducción. Aquí encontrarás de todo, desde pedir el móvil a una chica, que hacer cuando llamas, que decirles, como comportarte… Es una completa guía gratuita sobre seduccción que puedes aprovecharla al máximo (Actualmene aún la estoy desarrollando, disculpad por las molestias)",
                "Sin requisitos",
                9652, generaRanking()));
        cursos.add(new Curso(R.drawable.curso19, R.drawable.instructor19,
                "Mecánica para tu vocho",
                "Guillermo Vara de Gante",
                "¡Bienvenido!\n" +
                        "\n" +
                        "En esta capacitación conocerás el funcionamiento y las partes que integran un vehículo para poder hacer un diagnóstico, dar mantenimiento y reparar las fallas más comunes en su sistema mecánico, eléctrico y electrónico.  \n" +
                        "\n" +
                        "¡Éxito en tu capacitación!",
                "Sin requisitos",
                4086, generaRanking()));
        cursos.add(new Curso(R.drawable.curso20, R.drawable.instructor20,
                "Pintura al NO-Oleo",
                "Hugo Fernando Sánchez",
                "¿Sueñas con expresarte a través de la pintura y el color? Si nunca has pintado pero el gusanillo del arte te persigue, Emagister México tiene el gusto de presentarte el Taller libre de Pintura Roma Sur, donde podrás aprender diversas técnicas pictóricas para desarrollar tu talento artístico.\n" +
                        "\n" +
                        "El taller no requiere inscripción y está disponible en diversos horarios, incluyendo los sábados, por lo que podrás optar por el día y la hora que más le convengan a tu agenda. Del mismo modo, puedes unirte en cualquier época del año, así que ¡no hay pretexto para no agarrar el pincel! ",
                "Sin requisitos",
                2475, generaRanking()));
        cursos.add(new Curso(R.drawable.curso21, R.drawable.instructor21,
                "Maestro de Tatuajes",
                "Ines Vázquez Zempoalteca",
                "¿Cuando te tatúas piensas en Thomas Alva Edison? Seguramente no, pero deberías. \n" +
                        "\n" +
                        "Su relación con los tatuajes es mucho más cercana de lo que crees. El inventor tiene en su haber el fonógrafo y el teléfono, inventos de gran valor que destacaron entre todos hasta ser parte de nuestro presente. Edison tenía como objetivo principal crear objetos que fueran eficientes y de utilidad en la vida diaria de las personas, por ello creó una máquina que sería de gran ayuda para los oficinistas, pues les ahorraría trabajo y les facilitaría el mismo.",
                "Sin requisitos",
                9673, generaRanking()));
        cursos.add(new Curso(R.drawable.curso22, R.drawable.instructor22,
                "Aprende Ajedrez",
                "Karen Osiriz Solano Rangel",
                "¡Qué tal amigos! Sean bienvenidos a este sencillo y práctico curso de ajedrez elaborado por ustedes y para ustedes. Desde hace algunos meses en esta plataforma se vislumbraba desarrollar y colocar a su consideración un curso instructivo de ajedrez, en donde usted aprendiera los principios básicos del llamado “juego ciencia”, y poco a poco, con el desarrollo de cada una de las lecciones y con la práctica, se convirtiera usted en todo un ajedrecista experto.",
                "Sin requisitos",
                5000, generaRanking()));
    }

    private double generaRanking() {
        return 2 + ranking.nextDouble() * 3;
    }
}
