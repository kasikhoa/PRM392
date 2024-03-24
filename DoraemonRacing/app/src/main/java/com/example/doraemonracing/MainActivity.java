package com.example.doraemonracing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView Winner, LoseMessage, LoseMoney, ResultPlaying, Error;
    EditText Balance;
    CheckBox Cb1, Cb2, Cb3, Cb4, Cb5;
    SeekBar Sb1, Sb2, Sb3, Sb4, Sb5;
    EditText MoneyBetRunner1, MoneyBetRunner2, MoneyBetRunner3, MoneyBetRunner4, MoneyBetRunner5;
    EditText AmountOfMoney;
    Button chargeButton;
    Button btnStart;
    ImageButton btnExit, btnRule;

    MediaPlayer carBg;
    MediaPlayer backgroundMusic;
    MediaPlayer button1;
    MediaPlayer button2;
    MediaPlayer win;
    MediaPlayer lose;

    RelativeLayout PopUpContainer;

    @SuppressLint("WrongViewCast")
    private void referenceElements() {
        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        carBg = MediaPlayer.create(this, R.raw.car);
        button1 = MediaPlayer.create(this, R.raw.button1);
        button2 = MediaPlayer.create(this, R.raw.button2);
        win = MediaPlayer.create(this, R.raw.win);
        lose = MediaPlayer.create(this, R.raw.lose);
        MoneyBetRunner1 = findViewById(R.id.editTextNumber2);
        MoneyBetRunner2 = findViewById(R.id.editTextNumber3);
        MoneyBetRunner3 = findViewById(R.id.editTextNumber4);
        MoneyBetRunner4 = findViewById(R.id.editTextNumber5);
        MoneyBetRunner5 = findViewById(R.id.editTextNumber6);
        AmountOfMoney = findViewById(R.id.moneyAdd);
        chargeButton = findViewById(R.id.btnCharge);

        Balance = findViewById(R.id.totalMoney);

        Cb1 = findViewById(R.id.checkBoxNobita);
        Cb2 = findViewById(R.id.checkBoxShizuka);
        Cb3 = findViewById(R.id.checkBoxDoraemon);
        Cb4 = findViewById(R.id.checkBoxSuneo);
        Cb5 = findViewById(R.id.checkBoxChaien);

        Sb1 = findViewById(R.id.sb_race_nobita_1);
        Sb2 = findViewById(R.id.sb_race_shizuka_2);
        Sb3 = findViewById(R.id.sb_race_doraemon_3);
        Sb4 = findViewById(R.id.sb_race_suneo_4);
        Sb5 = findViewById(R.id.sb_race_chaien_5);

        btnStart = findViewById(R.id.btnStart);
        btnExit = findViewById(R.id.imageButton);
        btnRule = findViewById(R.id.imageButton2);
        PopUpContainer = findViewById(R.id.popupContainer);

        Sb1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        Sb2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        Sb3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        Sb4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        Sb5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


    }

    private int CheckMaxBetRunner() {
        int maxCount = 0;

        if (Cb1.isChecked()) {
            maxCount++;
        }
        if (Cb2.isChecked()) {
            maxCount++;
        }
        if (Cb3.isChecked()) {
            maxCount++;
        }
        if (Cb4.isChecked()) {
            maxCount++;
        }
        if (Cb5.isChecked()) {
            maxCount++;
        }
        return maxCount;
    }

    /* ----- Hàm tính toán tiền sau khi đã chơi xong ----- */
    private Type CalculateBalanceAfterPlay(List<Integer> horseWinners) {
        int Earn = 0, Lost = 0;
        int betAmount1 = Integer.parseInt(MoneyBetRunner1.getText().toString());
        int betAmount2 = Integer.parseInt(MoneyBetRunner2.getText().toString());
        int betAmount3 = Integer.parseInt(MoneyBetRunner3.getText().toString());
        int betAmount4 = Integer.parseInt(MoneyBetRunner4.getText().toString());
        int betAmount5 = Integer.parseInt(MoneyBetRunner5.getText().toString());
        int totalBetAmount = betAmount1 + betAmount2 + betAmount3 + betAmount4 + betAmount5;

        for (int i = 0; i < horseWinners.size(); i++) {
            switch (horseWinners.get(i)) {
                case 1:
                    if (Cb1.isChecked()) {
                        Earn += betAmount1 * 0.8 + betAmount1;
                    }
                    break;
                case 2:
                    if (Cb2.isChecked()) {
                        Earn += betAmount2 * 0.8 + betAmount2;
                    }
                    break;
                case 3:
                    if (Cb3.isChecked()) {
                        Earn += betAmount3 * 0.8 + betAmount3;
                    }
                    break;
                case 4:
                    if (Cb4.isChecked()) {
                        Earn += betAmount4 * 0.8 + betAmount4;
                    }
                    break;
                case 5:
                    if (Cb5.isChecked()) {
                        Earn += betAmount5 * 0.8 + betAmount5;
                    }
                    break;
            }
        }
        Lost = totalBetAmount;
        return new Type(Earn, Lost);
    }

    /* ------ Hàm check bet ----- */
    private void CheckValidBet() {
        Cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateBalanceWhileBet(isChecked, Cb1, MoneyBetRunner1);
            }
        });

        Cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateBalanceWhileBet(isChecked, Cb2, MoneyBetRunner2);
            }
        });

        Cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateBalanceWhileBet(isChecked, Cb3, MoneyBetRunner3);
            }
        });

        Cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateBalanceWhileBet(isChecked, Cb4, MoneyBetRunner4);
            }
        });

        Cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UpdateBalanceWhileBet(isChecked, Cb5, MoneyBetRunner5);
            }
        });
    }

    private int getBalance() {
        return Integer.parseInt(Balance.getText().toString());
    }

    /*----- Update ví sau lúc bet keo -----*/
    private void UpdateBalanceWhileBet(Boolean typeChecked, CheckBox cb, EditText money) {
        int balance = getBalance();
        if (typeChecked) {
            if (balance <= 0) {
                Toast.makeText(MainActivity.this, "You have run out of money in your wallet !!!", Toast.LENGTH_SHORT).show();
                cb.setChecked(false);
                return;
            }
            int maxBetRunner = CheckMaxBetRunner();
            if (maxBetRunner > 2) {
                Toast.makeText(MainActivity.this, "Sorry you can't bet more than 2 runner", Toast.LENGTH_SHORT).show();
            }
            balance -= Integer.parseInt(money.getText().toString());
        } else {
            balance += Integer.parseInt(money.getText().toString());
        }

        Balance.setText(String.valueOf(balance));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        backgroundMusic.setLooping(true); // Set it to loop
        backgroundMusic.start(); // Start playing
        referenceElements();
        CheckValidBet();

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int speed = 10;
                String WinnerFirstPlace = "";
                stopAudio(AudioType.background);
                ArrayList<Integer> Winners = new ArrayList<>();
                Random random = new Random();
                int distance1 = random.nextInt(speed);
                int distance2 = random.nextInt(speed);
                int distance3 = random.nextInt(speed);
                int distance4 = random.nextInt(speed);
                int distance5 = random.nextInt(speed);

                if (Sb1.getProgress() >= Sb1.getMax()) {
                    if (WinnerFirstPlace.equals("")) {
                        WinnerFirstPlace = " 1st racer is winner";
                        Winners.add(1);
                    }
                }

                if (Sb2.getProgress() >= Sb2.getMax()) {
                    if (WinnerFirstPlace.equals("")) {
                        WinnerFirstPlace = " 2nd racer is winner";
                        Winners.add(2);
                    }
                }
                if (Sb3.getProgress() >= Sb3.getMax()) {
                    if (WinnerFirstPlace.equals("")) {
                        WinnerFirstPlace = " 3rd racer is winner";
                        Winners.add(3);
                    }
                }
                if (Sb4.getProgress() >= Sb4.getMax()) {
                    if (WinnerFirstPlace.equals("")) {
                        WinnerFirstPlace = " 4th racer is winner";
                        Winners.add(4);
                    }
                }
                if (Sb5.getProgress() >= Sb5.getMax()) {
                    if (WinnerFirstPlace.equals("")) {
                        WinnerFirstPlace = " 5th racer is winner";
                        Winners.add(5);
                    }
                }

                if (Winners.size() == 1) {
                    Type type = CalculateBalanceAfterPlay(Winners);
                    stopAudio(AudioType.car);
                    if (type.winEarn == 0) {
                        showPopupLoser(type.loseLost);
                    } else {
                        showPopupWinner(WinnerFirstPlace, type.winEarn, type.loseLost);
                    }
                    this.cancel();
                }

                Sb5.setProgress(Sb5.getProgress() + distance5);
                Sb4.setProgress(Sb4.getProgress() + distance4);
                Sb3.setProgress(Sb3.getProgress() + distance3);
                Sb2.setProgress(Sb2.getProgress() + distance2);
                Sb1.setProgress(Sb1.getProgress() + distance1);
            }

            @Override
            public void onFinish() {

            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(AudioType.button1);
                if (CheckMaxBetRunner() == 0) {
                    ShowPopupError("Please bet at least one person to play!");
                    return;
                } else if (CheckMaxBetRunner() > 2) {
                    ShowPopupError("Can not play!");
                    return;
                }
                int balance = getBalance();
                if (balance < 0) {
                    Toast.makeText(MainActivity.this, "You have run out of money in your wallet !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //stopAudio(AudioType.background);
                backgroundMusic.pause();
                playAudio(AudioType.car);
                countDownTimer.start();
                btnStart.setEnabled(false);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio(AudioType.background);
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        chargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moneyText = AmountOfMoney.getText().toString();
                if (!TextUtils.isEmpty(moneyText)) {
                    int moneyCharge = Integer.parseInt(moneyText);
                    int moneySet = Integer.parseInt(Balance.getText().toString()) + moneyCharge;
                    Balance.setText(String.valueOf(moneySet));
                    AmountOfMoney.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter an amount.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RuleActivity.class);
                startActivity(intent);
            }
        });
    } // end onCreate


    private void showPopupWinner(String winner, int earn, int lost) {

        //Declare Pop Up
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_winne, null);

        //Modify Size
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // Set true if you want to interact with views outside the popup

        //Setting Items In Pop Up
        Winner = popupView.findViewById(R.id.txt_winner);
        Winner.setText(winner);

        ResultPlaying = popupView.findViewById(R.id.txt_result_playing);

        if (earn != 0) {
            ResultPlaying.setText("You are win: " + earn + " ");
        }
        if (lost != 0) {
            ResultPlaying.setText(ResultPlaying.getText() + "|| You are deposit: " + lost);
        }

        // AUDIO MANAGER
        if (earn != 0 && lost != 0) {
            if (earn > lost) {
                playAudio(AudioType.win);
            } else {
                playAudio(AudioType.lose);
            }
        } else if (earn != 0) {
            playAudio(AudioType.win);
        } else {
            playAudio(AudioType.lose);
        }

        //Show Pop Up
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(PopUpContainer, Gravity.CENTER, 0, 0);

        // Dismiss the popup window when the close button is clicked
        Button closeButton = popupView.findViewById(R.id.popupButton);
        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        backgroundMusic.setLooping(true); // Set it to loop
        backgroundMusic.start();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(AudioType.button2);
                SetItemsToDefaultAndUpdateBalance(earn, lost);
                popupWindow.dismiss();

            }
        });
    }

    private void showPopupLoser(int lost) {
        playAudio(AudioType.lose);

        //Declare Pop Up
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_lose, null);

        //Modify Size
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // Set true if you want to interact with views outside the popup

        //Setting Items In Pop Up
        LoseMessage = popupView.findViewById(R.id.popupTitle);
        LoseMessage.setText("Sorry you are lose");

        LoseMoney = popupView.findViewById(R.id.txt_lose_lost);
        LoseMoney.setText("- " + lost);

        //Show Pop Up
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(PopUpContainer, Gravity.CENTER, 0, 0);

        // Dismiss the popup window when the close button is clicked
        Button closeButton = popupView.findViewById(R.id.popupButton);
        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        backgroundMusic.setLooping(true); // Set it to loop
        backgroundMusic.start();
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(AudioType.button2);
                SetItemsToDefaultAndUpdateBalance(0, lost);
                popupWindow.dismiss();

            }
        });
    }

    private void ShowPopupError(String error) {
        playAudio(AudioType.button1);

        //Declare Pop Up
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_error, null);

        //Modify Size
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // Set true if you want to interact with views outside the popup

        //Setting Items In Pop Up
        Error = popupView.findViewById(R.id.popupTitle);

        Error.setText(error);

        //Show Pop Up
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(PopUpContainer, Gravity.CENTER, 0, 0);

        // Dismiss the popup window when the close button is clicked
        Button closeButton = popupView.findViewById(R.id.popupButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(AudioType.button2);
                popupWindow.dismiss();
            }
        });
    }

    private void SetItemsToDefaultAndUpdateBalance(int earn, int lose) {

        btnStart.setEnabled(true);

        Sb1.setProgress(0);
        Sb2.setProgress(0);
        Sb3.setProgress(0);
        Sb4.setProgress(0);
        Sb5.setProgress(0);

        Cb1.setChecked(false);
        Cb2.setChecked(false);
        Cb3.setChecked(false);
        Cb4.setChecked(false);
        Cb5.setChecked(false);

        int balance = Integer.parseInt(Balance.getText().toString());

        if (earn != 0) {
            balance += earn;
        }
        if (lose != 0) {
            balance -= lose;
        }
        Balance.setText(String.valueOf(balance));
    }

    private void stopAudio(AudioType typeToPlay) {
        switch (typeToPlay) {
            case button1:
                button1.stop();
                break;
            case button2:
                button2.stop();
                break;
            case win:
                win.stop();
                break;
            case lose:
                lose.stop();
                break;
            case background:
                backgroundMusic.stop();
                break;
            case car:
                carBg.stop();
                break;
        }
    }

    private void playAudio(AudioType typeToPlay) {
        switch (typeToPlay) {
            case button1:
                button1.start();
                break;
            case button2:
                button2.start();
                break;
            case win:
                win.start();
                break;
            case lose:
                lose.start();
                break;
            case background:
                float volume = (float) (1 - (Math.log(50 - 25) / Math.log(50)));
                backgroundMusic.setVolume(volume, volume);
                backgroundMusic.start();
                break;
            case car:
                carBg = MediaPlayer.create(this, R.raw.car);
                carBg.start();
                break;
        }
    }

    private enum AudioType {
        button1, button2, win, lose, background, car
    }

    public class Type {
        public int winEarn;
        public int loseLost;

        public Type(int winEarn, int loseLost) {
            this.winEarn = winEarn;
            this.loseLost = loseLost;
        }
    }

}