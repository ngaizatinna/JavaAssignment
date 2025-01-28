# JavaAssignment
package educationlearningappswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EducationLearningAppSwing {
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JTextField answerField;
    private JButton nextButton;
    private int questionIndex = 0;
    private String[] currentQuiz;
    private String[] currentAnswers;
    private String subject;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EducationLearningAppSwing app = new EducationLearningAppSwing();
            app.createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Education Learning App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        answerField = new JTextField();
        nextButton = new JButton("Next");

        bottomPanel.add(answerField, BorderLayout.CENTER);
        bottomPanel.add(nextButton, BorderLayout.EAST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNext();
            }
        });

        showSubjectSelection();
    }

    public void showSubjectSelection() {
        String[] options = {"Mathematics", "Science", "Geography"};
        int choice = JOptionPane.showOptionDialog(frame, "Please select a subject:", "Education Learning App",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                subject = "Mathematics";
                startMathQuiz();
                break;
            case 1:
                subject = "Science";
                startScienceQuiz();
                break;
            case 2:
                subject = "Geography";
                startGeographyQuiz();
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Invalid choice. Please choose a valid subject.");
        }
    }

    public void startMathQuiz() {
        currentQuiz = new String[] {
                "What is 5 + 3?",
                "What is the square of 6?"
        };
        currentAnswers = new String[] {"8", "36"};
        showQuestion();
    }

    public void startScienceQuiz() {
        currentQuiz = new String[] {
                "What is the chemical symbol for water?",
                "What planet is known as the Red Planet?"
        };
        currentAnswers = new String[] {"H2O", "Mars"};
        showQuestion();
    }

    public void startGeographyQuiz() {
        currentQuiz = new String[] {
                "What is the capital city of Tanzania?",
                "Which ocean is located to the east of Africa?"
        };
        currentAnswers = new String[] {"Dodoma", "Indian Ocean"};
        showQuestion();
    }

    public void showQuestion() {
        if (questionIndex < currentQuiz.length) {
            textArea.setText(currentQuiz[questionIndex]);
            answerField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "End of " + subject + " Quiz.");
            questionIndex = 0; // Reset for next session
            showSubjectSelection(); // Show subject selection again
        }
    }

    public void handleNext() {
        String userAnswer = answerField.getText().trim();
        String correctAnswer = currentAnswers[questionIndex];

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(frame, "Correct!");
        } else {
            JOptionPane.showMessageDialog(frame, "Incorrect! The correct answer is " + correctAnswer + ".");
        }

        questionIndex++;
        showQuestion();
    }
}
