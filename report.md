# Capitolo 1 - Analisi
## 1.1 Requisiti
Il software vuole riprendere il celebre videogioco 2d platform Super Sonic 1991. Il giocatore controlla Sonic, che dovrà superare nemici e ostacoli per arrivare alla fine del livello, raccogliendo più anelli possibili, e sfruttando diversi power-up che potenziano Sonic.
### Requisiti funzionali
!!
- Il player deve:
    - poter muoversi verso destra, verso sinistra con velocità incrementale, e saltare
    - collezionare monete e ottenere power-ups che ne potenziano le abilità di gioco
    - sconfiggere i nemici attraverso il salto
- I nemici devono:
    - ostacolare il player facendogli perdere vita al contatto
    - potersi muovere verso destra e verso sinistra con una loro logica
- 
### Requisiti non funzionali
!!
- Menù iniziale
- Il gameplay dovrà risultare fluido
- Il videogioco dovrà essere compatibile con la maggiorparte dei dispositivi e sistemi operativi
## 1.2 Analisi e modello del dominio
Il giocatore ha modo di muoversi all'interno di una mappa, la quale si compone di blocchi non compenetrabili, trappole, nemici e oggetti raccoglibili.
Sonic può collezionare monete per incrementare il punteggio della partita, raccogliere power-ups per potenziarsi e sconfiggere nemici tramite l'abilità di salto.
Insieme a Sonic, si muovono nella mappa i nemici, seguendo una logica prestabilita. Quando un nemico colpisce il personaggio, quest'ultimo subisce danno, decrementando la propria salute.
Nella mappa sono sparse delle trappole, che al contatto con Sonic, gli provocano qualche tipo di effetto, per esempio un danno oppure un effetto di qualche tipo.

# Capitolo 2 - Design
!!
## 2.1 Architettura
!!
## 2.2 Design dettagliato
### Bagnolini Matteo
### Gerarchia di entità di gioco

**Problema:** Gestire la definizione di varie entità di gioco, dotate di caratteristiche differenti. Si vuole minimizzare la ripetizione di codice e garantire estendibilità per future modifiche e feature aggiuntive.

**Soluzione:** Per gestire la definizione delle entità di gioco ho voluto utilizzare il pattern Composite, che permette di creare una gerarchia di classi. Si definisce quindi una struttura ad albero, dove la radice è `AbstractGameEntity`. Questa classe modella una generica entità di gioco, che può essere specializzata in blocco, anello e power-up (foglie dell'albero) e `AbstractMoveableEntity`. Una `AbstractMoveableEntity` rappresenta una generica entità che può muoversi nella mappa di gioco, come ad esempio il personaggio principale e i nemici (foglie dell'albero).
In questo modo ho ridotto la ripetizione di codice non necessario, poichè le classi "foglia" avrebbe dovuto implementare tutti metodi uguali tra di loro, e permetto di avere una buona estendibilità. Ad esempio per creare una nuova entità dotata di movimento (come un boss) è necessario solamente estendere la classe `AbstractMoveableEntity` ed aggiungere il codice dove si specificano le funzionalità aggiuntive della nuova entità.

### Movimento delle entità

**Problema:** Ogni `MoveableEntity` deve potersi muovere, e ogni entità dovrebbe avere la sua logica di movimento.

**Soluzione:** Per gestire il movimento delle entità ho utilizzato il pattern template method all'interno della classe astratta `AbstractMoveableEntity`. In questa classe ho infatti definito un metodo astratto `updateVelocity()`, che viene chiamato all'interno del metodo `move()` della classe stessa. In questo modo, quando si va a definire una classe concreta che estende `AbstractMoveableEntity`, si deve andare a specificare la logica con cui viene aggiornata la velocità dell'entità.

**Problema:** Ogni `MoveableEntity` dovrebbe avere una propria fisica di gioco specifica. Inoltre si vuole separare la gestione della fisica dall'entità stessa per avere più modularità del codice.

**Soluzione:**

### Massari Filippo

### Gestione e riconoscimento delle entità di gioco

**Problema:** Nel contesto del gioco, sono presenti diverse entità con caratteristiche e comportamenti distinti. Queste entità devono essere rappresentate in modo coerente all'interno del modello di gioco, della sua rappresentazione visiva e dell'interazione con il giocatore. Tuttavia, la gestione di queste entità può diventare complessa a causa delle loro diverse proprietà e comportamenti.

**Soluzione:** Per affrontare questo problema e garantire una gestione efficace delle entità di gioco, abbiamo introdotto l'enum `GameEntityType`. Questo enum fornisce una rappresentazione univoca di ciascuna entità di gioco, associandole a un tipo specifico che comprende informazioni cruciali come il percorso dello sprite associato e l'indice identificativo. Utilizzando GameEntityType, siamo in grado di stabilire una corrispondenza diretta tra le classi istanziate nel gioco e le loro rappresentazioni visive e funzionali. Questo semplifica notevolmente la gestione delle entità nel modello di gioco, consentendo una maggiore coerenza e facilitando lo sviluppo e la manutenzione del codice.

### Gerarchia dei Blocchi del Gioco

**Problema:** Nel nostro gioco, abbiamo diversi tipi di blocchi con comportamenti differenti. Alcuni blocchi possono essere raccolti dai giocatori, mentre altri rappresentano trappole che influenzano il giocatore. È necessario un sistema flessibile che permetta di gestire questi diversi comportamenti senza duplicare il codice e facilitando l'estensibilità futura.

**Soluzione:** Abbiamo progettato una gerarchia di interfacce per i blocchi del gioco. Questa gerarchia utilizza i design pattern Factory e Strategy per creare oggetti e definire comportamenti intercambiabili. Le interfacce principali sono `BlockEntity`, `Collectible`, `Trap` e `CollectibleFactory`. `BlockEntity` è l'interfaccia di base per tutti i blocchi, raccoglibili e non raccoglibili che comongono il mondo di gioco. `Collectible` estende `BlockEntity` e rappresenta blocchi che possono essere raccolti.`Trap` estende `BlockEntity` e rappresenta blocchi che possono attivare trappole.`CollectibleFactory` fornisce un metodo per creare oggetti `Collectible`.Sebbene non esista, data l'assenza di una molteplicità effettiva di trappole, sarrebbe stato nei nostri piani realizzare anche un'interfaccia `TrapFactory` per gestire la creazione di più tipi di trappole con effetti differenti.
Attraverso l'utilizzo di tale gerarchia di interfacce risulta molto semplice arrichire il gioco con nuovi racchoglibili e nuove trappole, infatti non sarà richiesta l'implentazione di nuove classi, e relativa gestione capillare, ma semplicemente si andranno ad implementare nuovi metodi all'interno delle factory.

### Gestione centralizzata del mondo di gioco

**Problema:** Gestione del mondo di gioco, in particolar modo il riconoscimento, la distinzione e l'interazione tra entità di gioco

**Soluzione:** Il mondo è rappresentato da un'interfaccia `World` essa preeenta metodi utili ad istaziare, aggiornare e confrontare tutte le entità di gioco, essa si serve dell'enum `GameEntityType` per istanziare le entità di gioco attraverso l'utilizzo delle factory, distinguerle tra loro e gestirne le iterazioni attraverso il puttern observer implementato grazie al cunnubio di `CollisionResolver` e `PlayerManager`. Il compito pricipale dell'interfaccia `World`, ovvero quello di gestire il caricamento del mondo, è realizzato dall'interfaccia `WorldLoader`. La gestione di quest'ultima separatamente, adottanto dunque il puttern strategy, e ed in sinergia con il puttern factory ne permentto una migliore chiarezza, semplicità, manutenibiltà ed espandibiltà.

### Gestione consequenziale e differenziata degli effetti dei power-up timerizzati

**Problema:** I power-up dotati di timer hanno una routine di gestione pressochè sempre identica che se gestita in maniera errata potrebbe condurre a malfuzionamenti dovuti ad una sovrapposizione di queti ultimi.

**Soluzione:** Per gestire in modo corretto gli effetti dei power-up timerizzati, abbiamo pensato un sistema di gestione sequenziale e differenziata, grazie all'interfaccia `CollectableEffect` che permette di ridurre la ripetizione di codice e rende altamente scalabile l'aggiunta futura di power-up dichiarando una factory di effetti che andrà a coadiuvare il lavoro di `CollectableFactory`.

