/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my.javaFinalGUI;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 *
 * @author zafir
 */
public class StudentQuiz extends javax.swing.JFrame {

  String filePath = "AnswerSheet.txt";
   String currentQuestion;
   String answerValue;
  int correctCount;
    int totalCount=1;
    float result;
    int AllQuestions;
  private static String  username;
  private static String userType;
  
int timerValue;
  Timer timer;
  int second,minute;
  String ddSecond,ddMinute;;
  DecimalFormat dFormat = new DecimalFormat("00");

  

  
    
    public StudentQuiz(String username, String userType) {
        
        
        setLocation(500,200);
        initComponents();
        allQuestion();
        loadNextQuestion();
    
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(AllQuestions); // Set the maximum value to the total number of questions
        jProgressBar1.setValue(correctCount); // Set the initial value to 1
        this.username = username;
        this.userType = userType;
       
        
        counterLabel.setText("00:00");
        second = 0;
        minute = timerValue;
        
        countDownTimer();
        timer.start();
    }

    public void countDownTimer(){
    
      timer = new Timer(1000, new ActionListener(){
      
      @Override
      public void actionPerformed(ActionEvent e){
      
            second--;
            ddSecond = dFormat.format(second);
            ddMinute = dFormat.format(minute);
            
            counterLabel.setText(ddMinute+":"+ddSecond);
            
            if (second ==-1){
            
                second = 59;
                minute--;
                counterLabel.setText(ddMinute + ""+ddSecond);
                
            
            }
            if(minute ==0 && second == 0){
            timer.stop();
            result = ((float) correctCount / AllQuestions) * 100;
                quizQuestion.setFont(quizQuestion.getFont().deriveFont(24f));
                quizQuestion.setText("you got " + correctCount + "/" + totalCount + "!");
                finishButton.setVisible(true);
                falseButton.setVisible(false);
                trueButton.setVisible(false);
                submitButton.setVisible(false);
                counterLabel.setVisible(false);
                storeResults();
            
            }
      }
      
      
      });

    
    
    
    }
    
    
    
    
    //stores Students results in a TXT file
    private void storeResults(){
        //store results of students into txt file to read later.
    
    try(BufferedWriter writer = new BufferedWriter(new FileWriter("StudentResult.txt", true))){
        
        writer.write(username+","+result);
        writer.newLine();
    
    }
    catch(IOException e){
        e.printStackTrace();
    }
    
    }
    
    //to calcualte how many total questions there are
    private void allQuestion(){
        
         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
     while ((line = br.readLine()) != null){
        
     AllQuestions++;
     }
        
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
  
    
    //To load questions from AnswerSheet
    private void loadNextQuestion() {
            finishButton.setVisible(false);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                
                String[] qANDa = line.split(",");
                if (qANDa.length == 3) {
                   
                    String questionValue = qANDa[0].replaceAll("\"", "");
                     answerValue = qANDa[1].replaceAll("\"", "");
                    String timerAsText = qANDa[2].replaceAll("\"", "");
                    timerValue = Integer.parseInt(timerAsText);
                     
                    if (currentQuestion == null) {
                        // Set the text of quizQuestion to the first questionValue
                   
                        quizQuestion.setText(questionValue);
                        currentQuestion = questionValue;
                        second = 0;
                        minute = timerValue; // Set initial timer value
                         countDownTimer();
                        break;
                    } else if (questionValue.equals(currentQuestion)) {
                       // Set the text of quizQuestion to the next questionValue
                        if ((line = br.readLine()) != null) {
                        qANDa = line.split(",");
                        questionValue = qANDa[0].replaceAll("\"", "");
                        answerValue = qANDa[1].replaceAll("\"", "");
                        timerAsText = qANDa[2].replaceAll("\"", "");
                         timerValue = Integer.parseInt(timerAsText);
                        quizQuestion.setText(questionValue);
                        currentQuestion = questionValue;
                         second = 0;
                          minute = timerValue;
                           countDownTimer();
                    } else {
                        // No more questions, display a message or take appropriate action
                           result = ((float) correctCount / AllQuestions) * 100;
                         quizQuestion.setFont(quizQuestion.getFont().deriveFont(24f));
                        quizQuestion.setText("you got "+correctCount+"/"+totalCount+"!");
                        finishButton.setVisible(true);
                         falseButton.setVisible(false);
                          trueButton.setVisible(false);
                          submitButton.setVisible(false);
                          counterLabel.setVisible(false);
                          storeResults();
                           
                    }
                        jProgressBar1.setValue(totalCount);
                    break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        trueButton = new javax.swing.JRadioButton();
        falseButton = new javax.swing.JRadioButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        submitButton = new javax.swing.JButton();
        quizQuestion = new javax.swing.JLabel();
        finishButton = new javax.swing.JButton();
        counterLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(trueButton);
        trueButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        trueButton.setText("True");

        buttonGroup1.add(falseButton);
        falseButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        falseButton.setText("False");

        submitButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        quizQuestion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        finishButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        finishButton.setText("Exit Quiz");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        counterLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(falseButton)
                            .addComponent(trueButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(quizQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(92, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finishButton)
                        .addGap(68, 68, 68))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(counterLabel)
                .addGap(128, 128, 128))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(quizQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(counterLabel)
                .addGap(10, 10, 10)
                .addComponent(trueButton)
                .addGap(28, 28, 28)
                .addComponent(falseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finishButton))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
       
       ButtonModel selectedButton = buttonGroup1.getSelection();  
        
      if (!trueButton.isSelected() && !falseButton.isSelected() ){
      
      JOptionPane.showMessageDialog(this, "Select True or False", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else {
       if (trueButton.isSelected() && trueButton.getActionCommand().equals(answerValue)){
          
           correctCount++;
      
      }
            else if (falseButton.isSelected() && falseButton.getActionCommand().equals(answerValue)){
          
           correctCount++;
      
      }
        loadNextQuestion();
      buttonGroup1.clearSelection();  
      totalCount++;   
     
      }
    
        
   

        
    }//GEN-LAST:event_submitButtonActionPerformed

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        // TODO add your handling code here:
          LoginPage1 backTologin = new LoginPage1();
  backTologin.setVisible(true);
        this.dispose();
         
        
    }//GEN-LAST:event_finishButtonActionPerformed
public void close(){
    
    WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
        
    }


 

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentQuiz( username, userType).setVisible(true);
              
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel counterLabel;
    private javax.swing.JRadioButton falseButton;
    private javax.swing.JButton finishButton;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel quizQuestion;
    private javax.swing.JButton submitButton;
    private javax.swing.JRadioButton trueButton;
    // End of variables declaration//GEN-END:variables
}
