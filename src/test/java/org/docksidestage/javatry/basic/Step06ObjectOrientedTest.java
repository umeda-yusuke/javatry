/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.bizfw.basic.objanimal.*;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.DatabaseSql;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.os.MacOperationSystem;
import org.docksidestage.javatry.basic.st6.os.OldWindowsOperationSystem;
import org.docksidestage.javatry.basic.st6.os.St6OperationSystem;
import org.docksidestage.javatry.basic.st6.os.WindowsOperationSystem;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author umeda-yusuke
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // TODO umeyan 業務的な間違いがこの [buy one-day passport] のスコープに一つ by jflute (2024/08/05)
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        --quantity;
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        salesProceeds = oneDayPrice;

        //
        // [ticket info]
        //
        // simulation: actually these variables should be more wide scope
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        // done umeyan 単純な間違いが [do in park now!!!] のスコープに一つ by jflute (2024/08/05)
        // simulation: actually this process should be called by other trigger
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        }
        alreadyIn = true;

        //
        // [final process]
        //
        // TODO umeyan 実行してもログの結果を見ても気づかない系の間違いが [final process] のスコープにひとつ by jflute (2024/08/05)
        saveBuyingHistory(quantity, displayPrice, salesProceeds, alreadyIn);
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // simulation: only logging here (normally e.g. DB insert)
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
        }
    }
    // 修正した　by umeda-yusuke（2024/07/31）

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        //Ticket ticket = booth.buyOneDayPassport(10000);
        booth.buyOneDayPassport(10000); // as temporary, remove if you finished step05
        Ticket ticket = new Ticket(TicketType.ONE_DAY); // also here

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.isAlreadyIn());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    //　オブジェクトとは、プログラムの中でデータとそのデータに対する操作をまとめたものである。一塊にして行った結果、それは概念のような物に集約されていく。
    //　概念によっては、振る舞いをもっていたり、状態を持っていたりする。しかし、概念を完全にプログラムで表現しようとしたら、無数の状態・振る舞いを持たなければならない。
    //　そのため、オブジェクトは、その概念の中で仕様を必要十分に満たすように、状態と振る舞いを持つように設計される。べきだと思っている。
    // _/_/_/_/_/_/_/_/_/_/
    // done umeyan [いいね] すごい、特に "概念の中で仕様を必要十分に満たすよう" というのが良いですね by jflute (2024/08/06)
    // また、"それは概念のような物に集約されていく" というのも趣がありますね。設計アプローチを考えさせる言葉だと思います。
    // 前、久保さんと話した時にこんな感じの事を言っていたので笑　by umeda-yusuke（2024/08/06）
    // done jflute 1on1にてこの辺、こう思った背景を聞かせてください (2024/08/06)
    // o アイスの話をしたときを思い出して...
    // o トップダウン、ボトムアップの設計アプローチのお話少し

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = dog.getHitPoint();
        log(land); // your answer? => 10
    }
    // landを間違えた。ちゃんとコードを読まず、getInitialHitPointの値を見てしまった。by umeda-yusuke（2024/07/31）
    // barkメソッドを実行すると、breatheIn, prepareAbdominalMuscle, doBarkの中でdownHitPointが呼ばれる。
    // その結果、hitPointが3回減少する。そのため、10 - 3 * 1 = 7 となる。
    // done umeyan [いいね] 間違えた後のリカバリが素晴らしいです(^^ by jflute (2024/08/06)

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }
    // 合ってた。by umeda-yusuke（2024/07/31）

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }
    // 合ってた。by umeda-yusuke（2024/07/31）

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }
    // 合ってた。by umeda-yusuke（2024/07/31）

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 5
    }
    // 合ってた。by umeda-yusuke（2024/07/31）
    // Catクラスのbarkメソッドは、AnimalクラスのbarkメソッドやdownHitPointメソッドをオーバーライドしている。
    // そのため、hitPointの減り方が違う。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark(); // breathInCount = 1
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
    }
    // 合ってた。by umeda-yusuke（2024/07/31）
    // Zombieクラスは、getInitialHitPointメソッドをオーバーライドしている。
    // そして、downHitPointメソッドをオーバーライドしているため、hitPointが減少しない。

    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        // Dog, CatはAnimalクラスを継承しているため、Animal型の変数に代入できる。
        // 継承の嬉しいポイントは、コンピュータにDogとCatは同じ種類のデータであると認識させることができる事なのでは？と思っている。
        // また、呼び出せるメソッドに制限をかける事が出来るため、プログラムの保守性が向上すると思っている。
        // ただ現在、メソッドの追跡が大変でAnimal型を使うメリットがあまり分からない。
        // _/_/_/_/_/_/_/_/_/_/
        // done umeyan [いいね] "同じ種類のデータである" という言葉がポイントを得ていますね by jflute (2024/08/06)
        // "メソッドの追跡が大変" というのもこういった構造を考える上での実務的な問題の根本と言えます。
        // done jflute 1on1にて背景を聞かせてください (2024/08/06)
        // ポリモーフィズムの話をdoAnimalメソッドを題材にお話。メソッドの流れの処理が再利用できる。
        // メソッド内だけの話でも、自分でチェックが掛けられるのと、読み手に伝えやすくなる。
        //
        // メソッドの追跡が大変の話、人類のトレードオフ:
        // o 多少リーディングの鍛錬は必要
        // o エディターの機能を駆使して追跡も必要
        // o 図を描いて頭の中に構造イメージを湧かせる
        // done jflute 備忘録: 図を描いて頭の中に構造イメージを湧かせる話をし忘れたので次の1on1にて (2024/08/06)
        
        // TODO done umeyan [読み物課題] ホワイトボードを買ってこよう by jflute (2024/08/08)
        // https://jflute.hatenadiary.jp/entry/20110607/1307440686

        // TODO done umeyan [読み物課題] SIとスタートアップの違いを知ろう by jflute (2024/08/08)
        // https://jflute.hatenadiary.jp/entry/20151007/sista
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo
    }
    // 合ってた。by umeda-yusuke（2024/08/01）
    // soundLoudlyメソッドは、bark().getBarkWord()を呼び出している。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
    }
    // 合ってた。by umeda-yusuke（2024/08/01）
    // AlarmClockクラスは、Animalクラスを継承していないため、falseが返る。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    }
    // 合ってた。by umeda-yusuke（2024/08/01）
    // Catクラスは、FastRunnerインターフェースを実装しているため、trueが返る。
    // Zombieクラスは、FastRunnerインターフェースを実装していないため、falseが返る。

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
        Animal dog = new Dog();
        boolean sea = dog instanceof FastRunner;
        log(sea);
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        // 確かに何が違うんだろう。。。
        // インターフェイスに対しては、実装クラスがある。
        // インターフェイスは、クラスの振る舞いを定義するために使われる。
        // 抽象クラスは、処理を再利用するために使われる。

        // インタフェースには出来る事のみを定義し、具体的なことは実装クラスにお任せする。
        // →　詳細は見せず出来ることを定義し、外から使う人のため
        //　抽象クラスは、中で処理をまとめたもの。→　中で使う人のためのもの

        // 参考サイト
        // https://qiita.com/yoshinori_hisakawa/items/cc094bef1caa011cb739
        // _/_/_/_/_/_/_/_/_/_/
        // done umeyan [いいね] 素晴らしい、参考サイトも興味深いこと書いていますね by jflute (2024/08/06)
        // done jflute 1on1にてさらにお話聞かせてください (2024/08/06)
        // o Javaはオブジェクト指向/インターフェースの両方の考え方が同居してる
        // o インターフェースの用途のひとつ、小さな機能にフォーカスを当ててオブジェクトの違った横のつながりを作る
        // o もうひとつ、ColorBoxのようなオブジェクト指向とうまく使い分け (これも大きな機能(ColorBox)として扱ってるとも言える)
        // o Javaは多重継承ができないので、オブジェクト指向とインターフェースのコラボレーションが求められる
        // o などなど色々な話をしました
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
        Animal bird = new Bird();
        String sea = bird.bark().getBarkWord();
        log(sea);
        int land = bird.getHitPoint();
        log(land);
    }
    // Birdクラスを作ってみた。by umeda-yusuke（2024/08/04）

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
        Bird bird = new Bird();
        bird.fly();
        log(bird.getHitPoint());
    }
    // Flyableインターフェースを作ってみた。by umeda-yusuke（2024/08/04）
    // Flyableインターフェースは、flyメソッドを持っている。
    
    // done jflute 1on1にて、インターフェースの名前どうする話をする予定 (-able? -er? or ?) (2024/08/06)

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
        DatabaseSql mySql = new St6MySql();
        DatabaseSql postgreSql = new St6PostgreSql();
        log(mySql.buildPagingQuery(10, 1));
        log(postgreSql.buildPagingQuery(10, 1));
    }
    // DatabaseSqlクラスを作ってみた。by umeda-yusuke（2024/08/04）
    // TODO umeyan "int offset = pageSize * (pageNumber - 1);" の処理を再利用したいところですね by jflute (2024/08/06)
    // (MySQLでもPostgreSQLでも全く同じ意味合いの処理なので)
    // TODO umeyan 抽象クラスの名前を考える時に、今存在しない新しいサブクラスを幾つか想像してみると良いです by jflute (2024/08/06)
    // 例えば、MySql, PostgreSql以外に何がありえるでしょうか？ (そもそもこいつら何だ？って感じかもですが、そこはググって調べてみて)

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
        St6OperationSystem mac = new MacOperationSystem("mac");
        St6OperationSystem windows = new WindowsOperationSystem("windows");
        St6OperationSystem oldWindows = new OldWindowsOperationSystem("oldWindows");

        log(mac.getOsType());
        log(mac.buildUserResourcePath("test"));
        log(windows.getOsType());
        log(windows.buildUserResourcePath("test"));
        log(oldWindows.getOsType());
        log(oldWindows.buildUserResourcePath("test"));
    }
    // St6OperationSystemクラスから、MacOperationSystem、WindowsOperationSystem、OldWindowsOperationSystemクラスを作ってみた。by umeda-yusuke（2024/08/04）
    // 共通部分をSt6OperationSystemクラスにまとめた。
    // done jflute 1on1にて再利用の思考課題する (時間あれば) (2024/08/06)
    // 意味が同じだから再利用する、たまたま同じ場合はしない
    // 概念化(タイトル化)するってのが色々キーポイント
    // TODO done umeyan [読み物課題] ちょうどboolean引数増える話したので、以下のブログも読んでみてください by jflute (2024/08/15)
    // メンテ不能の強者、引数リモコンパターン
    // https://jflute.hatenadiary.jp/entry/20160905/argremocon

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
        Animal dog = new Dog();
        for (int i = 0; i < 3; i++) {
            dog.bark();
            log(dog.getHitPoint());
        }
    }
    // BarkingProcessクラス内での処理で、hitPointが正常に減少していることを確認した。by umeda-yusuke（2024/08/05）
    // BarkingProcessクラスは、Animalクラスを引数に持っている。そのため、Animalクラスのメソッドを呼び出すことができる。
    // 正直めちゃくちゃ悩んでいる。breatheIn、prepareAbdominalMuscle、doBarkのメソッドをAnimalクラスに持たせるべきか、BarkingProcessクラスに持たせるべきか。
    // ゾンビクラスがbreatheInメソッドをオーバライドしているため、Animalクラスに持たせないと、ゾンビクラスが正常に動作しないのは分かっている。
    // しかし、それ以上にAnimalクラスにbreatheIn、prepareAbdominalMuscle、doBarkがあるのが違和感がある。よって、BarkingProcessクラスに持たせることにした。

    // TODO umeyan すでに悩んでいるようですが、Zombieの息を吸ったら吸った回数を日記に付けるという機能が失われてしまっています by jflute (2024/08/05)
    // BarkingProcess切り出しの影響ではあると思いますが、機能はキープしたまま切り出しができるようにしたいですね。
    // でも、breatheIn()やprepareAbdominalMuscle()がBarkingProcessにある事自体は悪くないと思います。
    // なぜなら、それらメソッドには // actually depends on barking というコメントがあるからです。
    // 汎用的なメソッドではなく、実質的に吠えるための専用の息吸いや腹筋ということなので。
    // もうちょい工夫すると、その状態でもZombieの息吸い日記をキープできるようになるのですが...
    // (新機能とかじゃないです。すでにstep6で体験している知識で実現できます)

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
    }
    // barkingパッケージを作成した。by umeda-yusuke（2024/08/05）
    // done umeyan [いいね] クラス階層がわかりやすくなりましたね by jflute (2024/08/06)
    // Javaはパッケージとディレクトリが一致しないといけない言語です。
    // つまり、Javaにおけるパッケージ分けというのは、普通にファイル管理のディレクトリ分けと同様のニュアンスが含まれます。
    // (個人差ありますが)ファイルがずらーっと並ぶで視認性が悪くなりますので、遠慮なくパッケージって分けて良いものということで。
    // ただ、どの単位で分けるか？なんて名前を付けるか？この辺はまた「デザイン」なんですよね。
    // done jflute 1on1にて現場でのパッケージ理由のジレンマの話する (この話ブログでまとめたいなぁ) (2024/08/06)

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it corrent?
        // 適切でないと思う。ゾンビクラスだけbreatheInメソッドをオーバライドしているが、本来Animalクラスが持つべきメソッドではないと思っている。
        // そのため、Animalクラスのサブクラスとして適切でないと思っている。
        // いや、↑のことは理由にならんな。しかし、ゾンビは動物じゃないから適切じゃないと思っている。理由は上手く言えない。
        // _/_/_/_/_/_/_/_/_/_/
        // done umeyan [いいね] 考えてくれてありがとうございます。言葉にするの難しいですよね by jflute (2024/08/06)
        // done jflute 1on1にてフォロー予定 (2024/08/06)
        // "ゾンビは動物じゃない" って素晴らしい言葉で「is-aの関係」をまずを確認してみる
    }

    // TODO done umeyan [読み物課題] ちょうど近くの先輩から学ぶ話があったので、こちらのブログも読んでみてください by jflute (2024/08/15)
    // こうはい extends せんぱい
    // https://jflute.hatenadiary.jp/entry/20131124/extendsmaster
}
