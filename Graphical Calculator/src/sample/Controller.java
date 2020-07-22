package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    double firstOperand,secondOperand;
    int operation;
    @FXML
    private TextField answerField;

    @FXML
    private Button equalButton;

    @FXML
    private Button divideButton;

    @FXML
    private Button minusButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button oneButton;

    @FXML
    private Button threeButton;

    @FXML
    private Button sevenButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button sixButton;

    @FXML
    private Button nineButton;

    @FXML
    private Button zeroButton;

    @FXML
    private Button multiplyButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button fiveButton;

    @FXML
    void handleButtonClick(ActionEvent event) {
        if(event.getSource()==oneButton){
            answerField.setText(answerField.getText()+1);
        }
        if(event.getSource()==twoButton){
            answerField.setText(answerField.getText()+2);
        }
        if(event.getSource()==threeButton){
            answerField.setText(answerField.getText()+3);
        }
        if(event.getSource()==fourButton){
            answerField.setText(answerField.getText()+4);
        }
        if(event.getSource()==fiveButton){
            answerField.setText(answerField.getText()+5);
        }
        if(event.getSource()==sixButton){
            answerField.setText(answerField.getText()+6);
        }
        if(event.getSource()==sevenButton){
            answerField.setText(answerField.getText()+7);
        }
        if(event.getSource()==eightButton){
            answerField.setText(answerField.getText()+8);
        }
        if(event.getSource()==nineButton){
            answerField.setText(answerField.getText()+9);
        }
        if(event.getSource()==zeroButton){
            answerField.setText(answerField.getText()+0);
        }
        if(event.getSource()==clearButton){
            answerField.setText("");
        }
        if(event.getSource()==plusButton){

            firstOperand = Double.parseDouble(answerField.getText());
            operation=1;
            answerField.setText("");

        }
        if(event.getSource()==minusButton){
            firstOperand = Double.parseDouble(answerField.getText());
            operation=2;
            answerField.setText("");

        }
        if(event.getSource()==multiplyButton){
            firstOperand = Double.parseDouble(answerField.getText());
            operation=3;
            answerField.setText("");

        }
        if(event.getSource()==divideButton){
            firstOperand = Double.parseDouble(answerField.getText());
            operation=4;
            answerField.setText("");
        }
        if(event.getSource()==equalButton){
            secondOperand = Double.parseDouble(answerField.getText());
            switch (operation){
                case 1:
                    double ans = firstOperand + secondOperand;
                    answerField.setText(String.valueOf(ans));
                    break;
                case 2:
                    ans = firstOperand - secondOperand;
                    answerField.setText(String.valueOf(ans));
                    break;
                case 3:
                    ans = firstOperand * secondOperand;
                    answerField.setText(String.valueOf(ans));
                    break;
                case 4:
                    ans=0;
                    try{
                        ans = firstOperand / secondOperand;
                    }catch (Exception e) {answerField.setText("Error");}
                    answerField.setText(String.valueOf(ans));
                    break;
            }
        }
    }
}
