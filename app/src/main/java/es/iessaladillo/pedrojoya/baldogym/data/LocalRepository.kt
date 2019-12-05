package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import java.util.concurrent.ThreadLocalRandom

object LocalRepository : Repository {

    // TODO:

    private fun createWeekSchedule(): List<TrainingSession> {

        data class SessionType(val name: String, val photoResId: Int, val description: String)

        val bodyRoomSessionTypes: List<SessionType> = listOf(
            SessionType("Body combat", R.drawable.bodycombat, "Body combat es un programa de entrenamiento cardiovascular insparado en las artes marciales. Coreografiado en base a una buena música, con sus excelentes instructores, los participantes realizan golpes, puñetazos, patadas y katas, queman calorías y consiguen una mayor resistencia cardiovascular."),
            SessionType("Body step", R.drawable.bodystep, "Body step es un programa de entrenamiento que combina el trabajo cardiovascular con el de fuerza y resistencia muscular. A través de un buen entrenamiento interválico se consiguen resultados rápidamente."),
            SessionType("Body attack", R.drawable.bodyattack, "Body attack es un entrenamiento insipirado en movimientos de diferentes disciplinas deportivas. Mejora la resistencia cardiovascular y la fuerza y resistencia muscular. Es una clase intensa de entrenamiento interválico que combina movimientos aeróbicos atléticos con ejercicios de fuerza y estabilización postural."),
            SessionType("Body jump", R.drawable.bodyjump, "Body jump es un programa de ejercicios sobre un mini trampolín. La intensidad de la clase es media-alta. Aunque con una buena resistencia de base es apta para casi todos los públicos, ya que la lona del trampolín absorberá casi todo el impacto en cada salto y las articulaciones no sufren estrés. La coreografía es bastante sencilla de seguir, así que disfrutarás desde el primer día."),
            SessionType("Body pump", R.drawable.bodypump, "Body pump es la clase original con barra y discos que fortalece y tonifica todo el cuerpo. En una sesión de Bodypump trabajas los principales grupos musculares utilizando los mejores ejercicios de la sala de fitness, como, por ejemplo, squats, presses, elevaciones y cursl.")
        )
        val zenRoomSessionTypes: List<SessionType> = listOf(
            SessionType("Zumba", R.drawable.zumba, "Zumba es un programa de fitness que engloba elemento de danza y de aerobic. La coreografía de Zumba incorpora hip-hop, soca, samba, salsa, merengue, mambo y artes marciales. Incluye también sentadillas y estocadas. Los ejercicios incluyen música con ritmos rápidos y lentos, así como el entrenamiento de la resistencia."),
            SessionType("Sh'bam", R.drawable.shabam, "Sh'bam es un programa divertido que combina movimientos de baile sencillos y muy sexys, es perfecto para ponerte en forma y dejar salir al artista que llevas dentro."),
            SessionType("Latino", R.drawable.latino, "Latino mejora tu coordinación a ritmo de bachata, chachachá, reggaetón, samba o el mambo. Bailes latinos que mejoran tu autoestima. Es una actividad recomendada para todas las edades. No hace falta venir acompañado, el trabajo es individual."),
            SessionType("Pilates", R.drawable.pilates, "Pilates es una rutina de acondicionamiento corporal que puede ayudarte a ganar flexibilidad, fuerza muscular y resistencia en las piernas, abdominales, brazos, caderas y espalda. Pone énfasis en la alineación de la columna vertebral y la pelvis, en la respiración y en el desarrollo de un tronco fuerte y mejora la coordinación y el equilibrio."),
            SessionType("Yoga", R.drawable.yoga, "En el Yoga, como en la vida, se trata de encontrar la dicha y la serenidad del momento. Todo el mundo puede practicar yoga. Sin tener en cuenta la edad, la experiencia o el nivel de flexibilidad, todos podemos beneficiarnos del yoga, siempre y cuando mantengamos la flexibilidad mental y la paciencia. Las clases de yoga te equilibran el día.")
        )
        val times: List<String> = listOf(
            "17:00 - 18:00",
            "18:00 - 19:00",
            "19:00 - 20:00",
            "20:00 - 21:00",
            "21:00 - 22:00"
        )
        val bodyTrainers: List<String> = listOf(
            "Entrenador 1",
            "Entrenador 2",
            "Entrenador 3",
            "Entrenador 4"
        )
        val zenTrainers: List<String> = listOf(
            "Entrenador 5",
            "Entrenador 6",
            "Entrenador 7",
            "Entrenador 8"
        )

        var trainingSessionId: Long = 0

        val trainingSessions: MutableList<TrainingSession> = mutableListOf()
        WeekDay.values().forEach { weekDay ->
            val shuffledBodySessionTypes = bodyRoomSessionTypes.shuffled()
            times.forEachIndexed { index, time ->
                val sessionType = shuffledBodySessionTypes[index]
                trainingSessions.add(
                    TrainingSession(
                        ++trainingSessionId,
                        sessionType.name,
                        weekDay,
                        sessionType.photoResId,
                        sessionType.description,
                        time,
                        bodyTrainers[ThreadLocalRandom.current().nextInt(bodyTrainers.size)],
                        "Body Room",
                        ThreadLocalRandom.current().nextInt(50),
                        false
                    )
                )
            }
            val shuffledZenSessionTypes = zenRoomSessionTypes.shuffled()
            times.forEachIndexed { index, time ->
                val sessionType = shuffledZenSessionTypes[index]
                trainingSessions.add(
                    TrainingSession(
                        ++trainingSessionId,
                        sessionType.name,
                        weekDay,
                        sessionType.photoResId,
                        sessionType.description,
                        time,
                        zenTrainers[ThreadLocalRandom.current().nextInt(zenTrainers.size)],
                        "Zen Room",
                        ThreadLocalRandom.current().nextInt(50),
                        false
                    )
                )
            }
        }
        return trainingSessions
    }

}
