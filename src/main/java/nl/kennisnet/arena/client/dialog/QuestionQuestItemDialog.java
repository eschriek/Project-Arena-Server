package nl.kennisnet.arena.client.dialog;

import java.util.ArrayList;
import java.util.List;

import nl.kennisnet.arena.client.domain.QuestItemDTO;
import nl.kennisnet.arena.client.widget.FormTablePanel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

public class QuestionQuestItemDialog extends StoryQuestItemDialog {

   private TextBox[] answersTextBoxs;
   private RadioButton[] correctAnswer;

   public QuestionQuestItemDialog(final QuestItemDTO itemDTO,boolean readOnlyDialog,boolean create) {
      super(itemDTO,readOnlyDialog,create);
      setText("Vraag details");
   }

   private List<FormTablePanel.Element> createAnswerPanels(){
      List<FormTablePanel.Element> result=new ArrayList<FormTablePanel.Element>();
      answersTextBoxs=new TextBox[4];
      correctAnswer=new RadioButton[4];
      for (int i = 0; i < 4; i++) {
         Panel panel=new HorizontalPanel();
         answersTextBoxs[i]=new TextBox();
         answersTextBoxs[i].setMaxLength(80);
         panel.add(answersTextBoxs[i]);
         correctAnswer[i] = new RadioButton("correctAnswer");
         panel.add(correctAnswer[i]);
         result.add(new FormTablePanel.Element("Antwoord "+(i+1),panel));
      }         
      return result;
   }   


   @Override
   protected List<FormTablePanel.Element> createFormPanels(){
      List<FormTablePanel.Element> result=new ArrayList<FormTablePanel.Element>();
      result.add(createNamePanel());
      result.add(createStoryPanel("Vraag"));
      result.addAll(createAnswerPanels());
      result.add(createRadiusPanel());
      return result;
   }
   
   
   protected void fillFormFromItem(QuestItemDTO itemDTO){
      super.fillFormFromItem(itemDTO);
      answersTextBoxs[0].setText(itemDTO.getOption1());
      answersTextBoxs[1].setText(itemDTO.getOption2());
      answersTextBoxs[2].setText(itemDTO.getOption3());
      answersTextBoxs[3].setText(itemDTO.getOption4());
      if (correctAnswer!=null){
         for (int i = 0; i < correctAnswer.length; i++) {
            RadioButton correct= correctAnswer[i];
            if (itemDTO.getCorrectOption()!=null&&itemDTO.getCorrectOption()==i+1&&correct!=null){
               correct.setValue(true);
            } else {
               correct.setValue(false);
            }
            
         }
      }
   }
   
   
   protected QuestItemDTO fillItemFromForm(QuestItemDTO itemDTO){
      QuestItemDTO result=super.fillItemFromForm(itemDTO);
      itemDTO.setOption1(answersTextBoxs[0].getText());
      itemDTO.setOption2(answersTextBoxs[1].getText());
      itemDTO.setOption3(answersTextBoxs[2].getText());
      itemDTO.setOption4(answersTextBoxs[3].getText());

      if (correctAnswer!=null){
         for (int i = 0; i < correctAnswer.length; i++) {
            RadioButton correct= correctAnswer[i];
            if (correct!=null&&correct.getValue()){
               itemDTO.setCorrectOption(i+1);
            }
         }
      }

      
      return result;
   }
   
}
