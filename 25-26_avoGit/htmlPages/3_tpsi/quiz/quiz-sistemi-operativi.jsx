import { useState, useCallback } from "react";

const questions = [
  {
    id: 1,
    question: "Qual è il ruolo principale di un Sistema Operativo?",
    options: [
      "Eseguire solo programmi di videoscrittura",
      "Agire da intermediario tra utente e hardware, gestendo le risorse",
      "Compilare programmi in linguaggio macchina",
      "Collegare il computer a Internet"
    ],
    correct: 1,
    explanation: "Il SO è un gestore di risorse e un intermediario fra utente e hardware. Crea una \"macchina astratta\" più comoda rispetto alla macchina fisica sottostante."
  },
  {
    id: 2,
    question: "Nel periodo 1945–1955, chi era il programmatore, l'operatore e l'utente del computer?",
    options: [
      "Tre persone diverse specializzate",
      "Un team di almeno 5 persone",
      "La stessa persona svolgeva tutti i ruoli",
      "Non serviva un operatore, era tutto automatico"
    ],
    correct: 2,
    explanation: "Nel periodo senza SO, la stessa persona era progettista, programmatore, operatore e utente. I computer erano enormi, costosissimi e poco affidabili (valvole termoioniche)."
  },
  {
    id: 3,
    question: "Cosa si intende per \"throughput\"?",
    options: [
      "La velocità massima della CPU in GHz",
      "La quantità di memoria RAM disponibile",
      "La quantità di lavoro completato in un certo tempo",
      "Il numero di utenti collegati contemporaneamente"
    ],
    correct: 2,
    explanation: "Throughput = quantità di lavoro completato in un certo tempo. Maggiore è il throughput, migliori sono le prestazioni. Nei primi sistemi si facevano solo 1-2 job all'ora!"
  },
  {
    id: 4,
    question: "Cos'era il \"Resident Monitor\" nei primi sistemi batch (1955–1959)?",
    options: [
      "Uno schermo per monitorare i programmi",
      "Il primo sistema operativo: un programma sempre in memoria che caricava e gestiva i job",
      "Un tecnico che sorvegliava il mainframe",
      "Un dispositivo hardware per il controllo della temperatura"
    ],
    correct: 1,
    explanation: "Il Resident Monitor era il primo SO: un programma sempre in memoria che caricava automaticamente i programmi, passava la CPU al programma utente e riprendeva il controllo a fine esecuzione."
  },
  {
    id: 5,
    question: "Nei sistemi batch, quanto tempo la CPU restava in attesa a causa della lentezza dell'I/O?",
    options: [
      "Circa il 10%",
      "Circa il 50%",
      "Circa il 93,7%",
      "Circa il 75%"
    ],
    correct: 2,
    explanation: "La CPU era 15 volte più veloce dell'I/O (18.000 vs 1.200 schede/min), quindi restava inattiva per il 93,7% del tempo!"
  },
  {
    id: 6,
    question: "Cosa significa \"DMA\" e qual è la sua funzione?",
    options: [
      "Dynamic Memory Allocation: alloca memoria ai programmi",
      "Direct Memory Access: un controller trasferisce dati tra I/O e memoria senza coinvolgere la CPU",
      "Disk Management Architecture: gestisce i dischi rigidi",
      "Data Memory Adapter: converte i dati tra formati diversi"
    ],
    correct: 1,
    explanation: "DMA = Direct Memory Access. Un controller dedicato trasferisce dati tra I/O e memoria SENZA coinvolgere la CPU, che resta libera per altre operazioni."
  },
  {
    id: 7,
    question: "Cosa significa l'acronimo SPOOLING?",
    options: [
      "System Protocol for Online Operations and Loading",
      "Simultaneous Peripheral Operation On-Line",
      "Sequential Processing of Outgoing Online Links",
      "Shared Processing for Optimized Output Levels"
    ],
    correct: 1,
    explanation: "SPOOLING = Simultaneous Peripheral Operation On-Line. I dati vengono scritti su disco (buffer temporaneo) per separare la velocità dell'I/O da quella della CPU."
  },
  {
    id: 8,
    question: "Qual è il principio fondamentale della multiprogrammazione?",
    options: [
      "Eseguire un solo programma alla volta ma più velocemente",
      "Più programmi in memoria: quando uno aspetta l'I/O, la CPU passa a un altro",
      "Dividere un programma in più parti e compilarle in parallelo",
      "Collegare più computer in rete per eseguire lo stesso programma"
    ],
    correct: 1,
    explanation: "Nella multiprogrammazione, più programmi sono in memoria contemporaneamente. Quando un programma aspetta l'I/O, la CPU passa a un ALTRO programma, restando quasi sempre attiva."
  },
  {
    id: 9,
    question: "Cosa rese speciale l'IBM System/360?",
    options: [
      "Era il computer più piccolo mai costruito",
      "Fu il primo computer \"a famiglia\" con diversi modelli compatibili fra loro",
      "Era gratuito per le università",
      "Era il primo computer con interfaccia grafica"
    ],
    correct: 1,
    explanation: "L'IBM System/360 fu il primo computer \"a famiglia\" con diversi modelli compatibili fra loro. Ha definito un'epoca dell'informatica e dalla sua complessità nasce l'Ingegneria del Software."
  },
  {
    id: 10,
    question: "Cosa si intende per \"time slice\" nel time-sharing?",
    options: [
      "Il tempo necessario per compilare un programma",
      "L'intervallo di tempo in cui ogni programma riceve la CPU, poi viene interrotto (pre-emption)",
      "Il tempo di accensione del computer",
      "Il tempo che l'operatore impiega per caricare le schede perforate"
    ],
    correct: 1,
    explanation: "Nel time-sharing, ogni programma riceve la CPU per un intervallo di tempo (time slice). Scaduto il tempo, anche se non ha finito, la CPU passa al programma successivo (pre-emption)."
  },
  {
    id: 11,
    question: "Quale di questi concetti NON fu introdotto da MULTICS?",
    options: [
      "Memoria virtuale",
      "File system gerarchico",
      "Architettura a ring (livelli di privilegio)",
      "Interfaccia grafica (GUI)"
    ],
    correct: 3,
    explanation: "MULTICS introdusse il concetto di processo, gestione account, memoria virtuale, architettura a ring e file system gerarchico. Le GUI arrivarono dopo, negli anni '70-'80."
  },
  {
    id: 12,
    question: "Perché UNIX è considerato estremamente portabile?",
    options: [
      "Perché è open source",
      "Perché è scritto per circa il 60% in linguaggio C",
      "Perché funziona solo su un tipo di hardware",
      "Perché è molto piccolo e occupa poca memoria"
    ],
    correct: 1,
    explanation: "UNIX è scritto in C per circa il 60%, il che lo rende estremamente portabile su diverse architetture hardware. Da UNIX derivano Linux, macOS e Android."
  },
  {
    id: 13,
    question: "Qual è la differenza tra un sistema Hard Real-Time e Soft Real-Time?",
    options: [
      "Hard usa hardware più costoso, Soft usa hardware economico",
      "Hard richiede risposta entro un tempo assoluto (critico), Soft tollera ritardi senza danni gravi",
      "Hard è per i server, Soft è per i PC",
      "Non c'è differenza, sono sinonimi"
    ],
    correct: 1,
    explanation: "Nei sistemi Hard Real-Time la risposta DEVE arrivare in tempo (es. pilota automatico aereo). Nei Soft Real-Time un ritardo non causa danni gravi (es. streaming video)."
  },
  {
    id: 14,
    question: "Quale tra questi è un esempio di sistema Hard Real-Time?",
    options: [
      "Streaming video",
      "Prelievo al bancomat",
      "Pilota automatico di un aereo",
      "Elettrodomestici smart"
    ],
    correct: 2,
    explanation: "Il pilota automatico di un aereo è un sistema Hard Real-Time: la risposta DEVE arrivare entro il tempo stabilito, altrimenti le conseguenze possono essere catastrofiche."
  },
  {
    id: 15,
    question: "Nella struttura di un SO moderno, qual è il livello più vicino all'hardware?",
    options: [
      "L'interfaccia utente (CLI/GUI)",
      "Le applicazioni utente",
      "I gestori di sistema",
      "Il Kernel"
    ],
    correct: 3,
    explanation: "Il Kernel è il nucleo del SO, il livello più vicino all'hardware. Gestisce direttamente le risorse hardware (CPU, RAM, dischi, periferiche)."
  },
  {
    id: 16,
    question: "Cos'era il JCL nei primi sistemi batch?",
    options: [
      "Un linguaggio di programmazione ad alto livello",
      "Un dispositivo di input/output",
      "Un linguaggio per descrivere cosa caricare ed eseguire (Job Control Language)",
      "Un protocollo di rete"
    ],
    correct: 2,
    explanation: "JCL = Job Control Language. Le schede JCL descrivevano cosa caricare ed eseguire, permettendo l'automazione nei sistemi batch."
  },
  {
    id: 17,
    question: "Cos'è il \"turnaround time\"?",
    options: [
      "Il tempo di accensione del computer",
      "Il tempo dalla richiesta al completamento del job (minore = migliore)",
      "Il tempo necessario per cambiare programma",
      "Il tempo di vita utile di un mainframe"
    ],
    correct: 1,
    explanation: "Turnaround time = tempo dalla richiesta al completamento. Minore è il turnaround time, migliore è la prestazione del sistema."
  },
  {
    id: 18,
    question: "Quale standard del 1988 definì un'interfaccia API comune per i sistemi derivati da UNIX?",
    options: [
      "ISO 9001",
      "IEEE 802.11",
      "POSIX",
      "TCP/IP"
    ],
    correct: 2,
    explanation: "Nel 1988 fu definito lo standard POSIX, che fornisce un'interfaccia API comune per i sistemi operativi derivati da UNIX."
  },
  {
    id: 19,
    question: "Quali tre innovazioni degli anni '60 resero possibile la multiprogrammazione?",
    options: [
      "GUI, mouse, tastiera",
      "DMA, Interrupt, Spooling",
      "TCP/IP, Ethernet, Wi-Fi",
      "FORTRAN, COBOL, C"
    ],
    correct: 1,
    explanation: "DMA (trasferimento diretto in memoria), Interrupt (segnali hardware) e Spooling (buffer su disco) furono le tre innovazioni che, insieme ai dischi ad accesso casuale, resero possibile la multiprogrammazione."
  },
  {
    id: 20,
    question: "Da quale progetto nasce direttamente UNIX?",
    options: [
      "IBM System/360",
      "EDSAC",
      "MULTICS",
      "ARPANET"
    ],
    correct: 2,
    explanation: "UNIX nasce dalla difficoltà di rendere MULTICS un prodotto industriale. MULTICS fu sviluppato dal MIT, Bell Labs e General Electric."
  }
];

function shuffleArray(arr) {
  const shuffled = [...arr];
  for (let i = shuffled.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
  }
  return shuffled;
}

export default function QuizApp() {
  const [phase, setPhase] = useState("start");
  const [shuffledQuestions, setShuffledQuestions] = useState([]);
  const [currentQ, setCurrentQ] = useState(0);
  const [selected, setSelected] = useState(null);
  const [confirmed, setConfirmed] = useState(false);
  const [score, setScore] = useState(0);
  const [answers, setAnswers] = useState([]);

  const startQuiz = useCallback(() => {
    setShuffledQuestions(shuffleArray(questions));
    setCurrentQ(0);
    setSelected(null);
    setConfirmed(false);
    setScore(0);
    setAnswers([]);
    setPhase("quiz");
  }, []);

  const confirm = useCallback(() => {
    if (selected === null) return;
    setConfirmed(true);
    const q = shuffledQuestions[currentQ];
    const isCorrect = selected === q.correct;
    if (isCorrect) setScore((s) => s + 1);
    setAnswers((a) => [...a, { qId: q.id, selected, correct: q.correct, isCorrect }]);
  }, [selected, shuffledQuestions, currentQ]);

  const next = useCallback(() => {
    if (currentQ + 1 >= shuffledQuestions.length) {
      setPhase("results");
    } else {
      setCurrentQ((c) => c + 1);
      setSelected(null);
      setConfirmed(false);
    }
  }, [currentQ, shuffledQuestions.length]);

  const q = shuffledQuestions[currentQ];
  const pct = shuffledQuestions.length > 0 ? Math.round((score / shuffledQuestions.length) * 100) : 0;

  if (phase === "start") {
    return (
      <div style={{ minHeight: "100vh", background: "linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%)", display: "flex", alignItems: "center", justifyContent: "center", fontFamily: "'Segoe UI', system-ui, sans-serif", padding: 20 }}>
        <div style={{ textAlign: "center", maxWidth: 560, color: "#fff" }}>
          <div style={{ fontSize: 48, marginBottom: 8 }}>🖥️</div>
          <h1 style={{ fontSize: 28, fontWeight: 700, margin: "0 0 8px", letterSpacing: "-0.5px" }}>Quiz: Introduzione ai Sistemi Operativi</h1>
          <p style={{ color: "#94a3b8", fontSize: 15, margin: "0 0 12px" }}>TPSI – Classe 3ª • I.I.S. A. Avogadro</p>
          <div style={{ background: "rgba(255,255,255,0.06)", borderRadius: 16, padding: "24px 28px", margin: "20px 0", textAlign: "left", border: "1px solid rgba(255,255,255,0.08)" }}>
            <p style={{ color: "#cbd5e1", fontSize: 14, margin: "0 0 16px", lineHeight: 1.6 }}>
              20 domande a risposta multipla sull'evoluzione storica e i concetti fondamentali dei Sistemi Operativi. Le domande vengono presentate in ordine casuale.
            </p>
            <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 12 }}>
              {[
                ["📝", "20 domande"],
                ["🔀", "Ordine casuale"],
                ["✅", "Feedback immediato"],
                ["📊", "Punteggio finale"]
              ].map(([icon, text], i) => (
                <div key={i} style={{ background: "rgba(255,255,255,0.04)", borderRadius: 10, padding: "10px 14px", display: "flex", alignItems: "center", gap: 10 }}>
                  <span style={{ fontSize: 18 }}>{icon}</span>
                  <span style={{ color: "#e2e8f0", fontSize: 13 }}>{text}</span>
                </div>
              ))}
            </div>
          </div>
          <button onClick={startQuiz} style={{ background: "linear-gradient(135deg, #3b82f6, #2563eb)", color: "#fff", border: "none", borderRadius: 12, padding: "14px 48px", fontSize: 16, fontWeight: 600, cursor: "pointer", transition: "transform 0.15s", boxShadow: "0 4px 20px rgba(59,130,246,0.4)" }}
            onMouseOver={(e) => (e.target.style.transform = "scale(1.04)")}
            onMouseOut={(e) => (e.target.style.transform = "scale(1)")}>
            Inizia il Quiz
          </button>
        </div>
      </div>
    );
  }

  if (phase === "results") {
    const grade = pct >= 90 ? { emoji: "🏆", label: "Eccellente!", color: "#22c55e" } :
                  pct >= 70 ? { emoji: "👏", label: "Ottimo lavoro!", color: "#3b82f6" } :
                  pct >= 50 ? { emoji: "📚", label: "Sufficiente, ripassa un po'!", color: "#f59e0b" } :
                              { emoji: "💪", label: "Da ripassare, non mollare!", color: "#ef4444" };
    return (
      <div style={{ minHeight: "100vh", background: "linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%)", display: "flex", alignItems: "center", justifyContent: "center", fontFamily: "'Segoe UI', system-ui, sans-serif", padding: 20 }}>
        <div style={{ textAlign: "center", maxWidth: 480, color: "#fff" }}>
          <div style={{ fontSize: 56, marginBottom: 12 }}>{grade.emoji}</div>
          <h2 style={{ fontSize: 26, fontWeight: 700, margin: "0 0 6px" }}>{grade.label}</h2>
          <div style={{ margin: "20px auto", width: 140, height: 140, borderRadius: "50%", border: `5px solid ${grade.color}`, display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", background: "rgba(255,255,255,0.04)" }}>
            <span style={{ fontSize: 36, fontWeight: 800, color: grade.color }}>{score}/{shuffledQuestions.length}</span>
            <span style={{ fontSize: 13, color: "#94a3b8" }}>{pct}%</span>
          </div>
          <div style={{ background: "rgba(255,255,255,0.06)", borderRadius: 14, padding: "16px 20px", margin: "20px 0", textAlign: "left", border: "1px solid rgba(255,255,255,0.08)", maxHeight: 280, overflowY: "auto" }}>
            {answers.map((a, i) => {
              const origQ = questions.find((q) => q.id === a.qId);
              return (
                <div key={i} style={{ display: "flex", alignItems: "flex-start", gap: 10, padding: "8px 0", borderBottom: i < answers.length - 1 ? "1px solid rgba(255,255,255,0.06)" : "none" }}>
                  <span style={{ fontSize: 16, flexShrink: 0, marginTop: 1 }}>{a.isCorrect ? "✅" : "❌"}</span>
                  <span style={{ color: a.isCorrect ? "#86efac" : "#fca5a5", fontSize: 13, lineHeight: 1.5 }}>{origQ.question}</span>
                </div>
              );
            })}
          </div>
          <button onClick={startQuiz} style={{ background: "linear-gradient(135deg, #3b82f6, #2563eb)", color: "#fff", border: "none", borderRadius: 12, padding: "14px 44px", fontSize: 16, fontWeight: 600, cursor: "pointer", boxShadow: "0 4px 20px rgba(59,130,246,0.4)" }}
            onMouseOver={(e) => (e.target.style.transform = "scale(1.04)")}
            onMouseOut={(e) => (e.target.style.transform = "scale(1)")}>
            🔄 Riprova
          </button>
        </div>
      </div>
    );
  }

  return (
    <div style={{ minHeight: "100vh", background: "linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%)", display: "flex", flexDirection: "column", fontFamily: "'Segoe UI', system-ui, sans-serif", padding: 20 }}>
      {/* Progress */}
      <div style={{ maxWidth: 640, width: "100%", margin: "0 auto 20px" }}>
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 8 }}>
          <span style={{ color: "#94a3b8", fontSize: 13 }}>Domanda {currentQ + 1} di {shuffledQuestions.length}</span>
          <span style={{ color: "#3b82f6", fontSize: 13, fontWeight: 600 }}>Punteggio: {score}</span>
        </div>
        <div style={{ height: 4, background: "rgba(255,255,255,0.08)", borderRadius: 4 }}>
          <div style={{ height: "100%", width: `${((currentQ + (confirmed ? 1 : 0)) / shuffledQuestions.length) * 100}%`, background: "linear-gradient(90deg, #3b82f6, #60a5fa)", borderRadius: 4, transition: "width 0.4s ease" }} />
        </div>
      </div>

      {/* Question Card */}
      <div style={{ maxWidth: 640, width: "100%", margin: "0 auto", flex: 1, display: "flex", flexDirection: "column" }}>
        <div style={{ background: "rgba(255,255,255,0.06)", borderRadius: 18, padding: "28px 28px 20px", border: "1px solid rgba(255,255,255,0.08)", marginBottom: 16 }}>
          <h2 style={{ color: "#f1f5f9", fontSize: 18, fontWeight: 600, margin: 0, lineHeight: 1.5 }}>{q.question}</h2>
        </div>

        <div style={{ display: "flex", flexDirection: "column", gap: 10, marginBottom: 20 }}>
          {q.options.map((opt, i) => {
            let bg = "rgba(255,255,255,0.04)";
            let border = "1px solid rgba(255,255,255,0.08)";
            let textColor = "#e2e8f0";
            let icon = null;

            if (confirmed) {
              if (i === q.correct) {
                bg = "rgba(34,197,94,0.12)";
                border = "1px solid rgba(34,197,94,0.5)";
                textColor = "#86efac";
                icon = "✅";
              } else if (i === selected && i !== q.correct) {
                bg = "rgba(239,68,68,0.12)";
                border = "1px solid rgba(239,68,68,0.5)";
                textColor = "#fca5a5";
                icon = "❌";
              }
            } else if (i === selected) {
              bg = "rgba(59,130,246,0.15)";
              border = "1px solid rgba(59,130,246,0.5)";
              textColor = "#93c5fd";
            }

            return (
              <button key={i} onClick={() => !confirmed && setSelected(i)} disabled={confirmed}
                style={{ background: bg, border, borderRadius: 12, padding: "14px 18px", color: textColor, fontSize: 14, lineHeight: 1.5, textAlign: "left", cursor: confirmed ? "default" : "pointer", display: "flex", alignItems: "flex-start", gap: 12, transition: "all 0.2s" }}>
                <span style={{ fontWeight: 700, color: confirmed && i === q.correct ? "#22c55e" : confirmed && i === selected ? "#ef4444" : "#64748b", flexShrink: 0, marginTop: 1 }}>
                  {icon || String.fromCharCode(65 + i) + "."}
                </span>
                <span>{opt}</span>
              </button>
            );
          })}
        </div>

        {/* Explanation */}
        {confirmed && (
          <div style={{ background: "rgba(59,130,246,0.08)", borderRadius: 14, padding: "16px 20px", marginBottom: 16, border: "1px solid rgba(59,130,246,0.2)" }}>
            <p style={{ margin: 0, color: "#93c5fd", fontSize: 13, lineHeight: 1.7 }}>
              <strong style={{ color: "#60a5fa" }}>💡 Spiegazione: </strong>{q.explanation}
            </p>
          </div>
        )}

        {/* Action Button */}
        <div style={{ textAlign: "center", marginTop: "auto", paddingBottom: 20 }}>
          {!confirmed ? (
            <button onClick={confirm} disabled={selected === null}
              style={{ background: selected === null ? "rgba(255,255,255,0.06)" : "linear-gradient(135deg, #3b82f6, #2563eb)", color: selected === null ? "#64748b" : "#fff", border: "none", borderRadius: 12, padding: "13px 44px", fontSize: 15, fontWeight: 600, cursor: selected === null ? "not-allowed" : "pointer", boxShadow: selected !== null ? "0 4px 20px rgba(59,130,246,0.3)" : "none" }}>
              Conferma
            </button>
          ) : (
            <button onClick={next}
              style={{ background: "linear-gradient(135deg, #3b82f6, #2563eb)", color: "#fff", border: "none", borderRadius: 12, padding: "13px 44px", fontSize: 15, fontWeight: 600, cursor: "pointer", boxShadow: "0 4px 20px rgba(59,130,246,0.3)" }}>
              {currentQ + 1 >= shuffledQuestions.length ? "Vedi risultati" : "Prossima domanda →"}
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
