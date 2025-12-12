import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class DesktopPetApp extends JFrame {

    private String currentPetType = "cat";
    private final JLabel petImageLabel = new JLabel();

    private Pet pet;
    private final TaskManager taskManager = new TaskManager();
    private final Scheduler scheduler = new Scheduler(5000);

    private final JComboBox<String> petChoice =
            new JComboBox<>(new String[]{"Cat", "Dog", "Nittany Lion"});

    private final JLabel moodLabel = new JLabel("Mood: -");
    private final JLabel stateLabel = new JLabel("State: -");
    private final JLabel happinessLabel = new JLabel("Happiness: -");

    private final DefaultListModel<Task> taskListModel = new DefaultListModel<>();
    private final JList<Task> taskList = new JList<>(taskListModel);

    private final JTextField titleField = new JTextField(10);
    private final JTextField dueField = new JTextField(10);
    private final JComboBox<String> filterCombo =
            new JComboBox<>(new String[]{"All", "Today", "Completed"});

    // === COLORS ===
    private final Color BG_IMAGE = new Color(255, 235, 245);
    private final Color BG_MAIN  = BG_IMAGE;
    private final Color BG_PANEL = BG_IMAGE;
    private final Color BG_TASK  = BG_IMAGE;

    public DesktopPetApp() {
        super("Desktop Task Pet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildUI();
        pack();
        setMinimumSize(new Dimension(1100, 750));
        setLocationRelativeTo(null);
    }

    private void buildUI() {

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBackground(BG_MAIN);
        setContentPane(root);

        // ================= TOP BAR =================
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(BG_MAIN);

        JButton createPetBtn = new JButton("Create Pet");
        createPetBtn.addActionListener(e -> createPet());

        top.add(new JLabel("Pet Selector:"));
        top.add(petChoice);
        top.add(createPetBtn);
        root.add(top, BorderLayout.NORTH);

        // ================= CENTER =================
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(BG_MAIN);
        root.add(center, BorderLayout.CENTER);

        // ----- LEFT STATS PANEL (VERTICALLY CENTERED) -----
        JPanel petPanel = new JPanel();
        petPanel.setBackground(BG_PANEL);
        petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));
        petPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        moodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        happinessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playBtn = new JButton("Press me to play!");
        playBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        playBtn.addActionListener(e -> {
            if (pet != null) {
                pet.onTaskCompleted();
                refreshPet();
            }
        });

        petPanel.add(Box.createVerticalGlue());
        petPanel.add(moodLabel);
        petPanel.add(Box.createVerticalStrut(8));
        petPanel.add(stateLabel);
        petPanel.add(Box.createVerticalStrut(8));
        petPanel.add(happinessLabel);
        petPanel.add(Box.createVerticalStrut(15));
        petPanel.add(playBtn);
        petPanel.add(Box.createVerticalGlue());

        // ----- DIVIDER -----
        JSeparator divider = new JSeparator(SwingConstants.VERTICAL);
        divider.setPreferredSize(new Dimension(2, 1));
        divider.setForeground(Color.GRAY);

        // ----- CAT IMAGE PANEL -----
        JPanel imageSpace = new JPanel(new GridBagLayout());
        imageSpace.setBackground(BG_IMAGE);
        imageSpace.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        petImageLabel.setPreferredSize(new Dimension(280, 280));
        imageSpace.add(petImageLabel);

        // ===== GRID PLACEMENT =====
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        center.add(petPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 15, 0, 15);
        center.add(divider, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        center.add(imageSpace, gbc);

        // ================= BOTTOM TASK SYSTEM =================
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBackground(BG_TASK);
        taskPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        root.add(taskPanel, BorderLayout.SOUTH);

        JPanel addRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addRow.setBackground(BG_TASK);

        addRow.add(new JLabel("Title of Task:"));
        addRow.add(titleField);

        addRow.add(new JLabel("Due (mm-dd-yyyy):"));
        addRow.add(dueField);

        JButton addBtn = new JButton("Create Task");
        addBtn.addActionListener(e -> addTask());

        JButton completeBtn = new JButton("Done");
        completeBtn.addActionListener(e -> toggleComplete());

        addRow.add(addBtn);
        addRow.add(completeBtn);
        taskPanel.add(addRow, BorderLayout.NORTH);

        taskList.setVisibleRowCount(6);
        JScrollPane taskScroll = new JScrollPane(taskList);
        taskPanel.add(taskScroll, BorderLayout.CENTER);

        JPanel filterRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filterRow.setBackground(BG_TASK);

        filterRow.add(new JLabel("Filter:"));
        filterRow.add(filterCombo);

        JButton applyFilterBtn = new JButton("Apply");
        applyFilterBtn.addActionListener(e -> refreshTasks());
        filterRow.add(applyFilterBtn);

        taskPanel.add(filterRow, BorderLayout.SOUTH);
    }

    // ================= LOGIC =================

    private void createPet() {
        String choice = (String) petChoice.getSelectedItem();
        ThemeFactory factory;

        if ("Cat".equals(choice)) {
            factory = new CatThemeFactory();
            currentPetType = "cat";
        } else if ("Dog".equals(choice)) {
            factory = new DogThemeFactory();
            currentPetType = "dog";
        } else {
            factory = new LionThemeFactory();
            currentPetType = "lion";
        }

        pet = new StreakBonusDecorator(factory.createPet());
        scheduler.setPet(pet);
        scheduler.start(this::refreshPet);
        refreshPet();
    }

    private void addTask() {
        try {
            String title = titleField.getText().trim();
            String dueText = dueField.getText().trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate dueDate = LocalDate.parse(dueText, formatter);

            Task task = new Task(title, "", dueDate);
            taskManager.addTask(task);

            titleField.setText("");
            dueField.setText("");
            refreshTasks();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input\nDate format: mm-dd-yyyy",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toggleComplete() {
        Task t = taskList.getSelectedValue();
        if (t != null) {
            t.toggleCompleted();
            if (pet != null && t.isCompleted()) {
                pet.onTaskCompleted();
                refreshPet();
            }
            refreshTasks();
        }
    }

    private void refreshPet() {
        if (pet == null) return;
        moodLabel.setText("Mood: " + pet.getMoodLabel());
        stateLabel.setText("State: " + pet.getStateName());
        happinessLabel.setText("Happiness: " + pet.getHappiness());
        updatePetImage();
    }

    private void updatePetImage() {
        try {
            String state = pet.getStateName().toLowerCase();
            String fileName = currentPetType + "_" + state + ".png";

            ImageIcon icon = new ImageIcon("src/assets/" + fileName);
            Image scaled = icon.getImage().getScaledInstance(260, 260, Image.SCALE_SMOOTH);
            petImageLabel.setIcon(new ImageIcon(scaled));
            petImageLabel.setText("");

        } catch (Exception e) {
            petImageLabel.setIcon(null);
            petImageLabel.setText("Image missing");
        }
    }

    private void refreshTasks() {
        taskListModel.clear();
        TaskFilter filter =
                "Today".equals(filterCombo.getSelectedItem()) ? new TodayFilter() :
                        "Completed".equals(filterCombo.getSelectedItem()) ? new CompletedFilter() :
                                tasks -> tasks;

        for (Task t : taskManager.getFilteredTasks(filter))
            taskListModel.addElement(t);
    }
}
