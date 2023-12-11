package family_tree.model.service;

import family_tree.model.family.FamilyTree;
import family_tree.model.human.Gender;
import family_tree.model.human.Human;
import family_tree.model.writer.FileHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Service {
    private FamilyTree familyTree;
    private FileHandler fileHandler;


    public Service(FamilyTree tree) {
        this.familyTree = tree;
        fileHandler = new FileHandler();
    }

    public Service() {
        this(new FamilyTree<>());
    }


    public String getHumanListInfo(FamilyTree<Human> tree) {
        StringBuilder sb = new StringBuilder();
        sb.append("Список членов семьи:\n");
        for (Human human : tree) {
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void sortByName(FamilyTree tree) {
        tree.sortByName();
    }

    public void sortByName() {
        familyTree.sortByName();
    }

    public void sortByAge(FamilyTree tree) {
        tree.sortByAge();
    }

    public void sortByAge() {
        familyTree.sortByAge();
    }


    public void addFamilyMember(String gender, String name, String familyName, String strDate) {
        Gender newGender = Gender.valueOf(gender);
        LocalDate birthDate = getLocalDate(strDate);
        Human human = new Human(newGender, name, familyName, birthDate);
        familyTree.addFamilyMember(human);
    }

    public LocalDate getLocalDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strDate, formatter);
    }

    public String getFamilyTreeMembers() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список членов семьи:\n");
        for (Object human : familyTree) {
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void saveTreeToFile() {
        String filePath = "src/family_tree/model/writer/tree";
        System.out.printf("Данные успешно сохранены в файл? %b\n", fileHandler.write(familyTree, filePath));
    }

    public FamilyTree<Human> getTreeFromFile() {
        String filePath = "src/family_tree/model/writer/tree";
        familyTree = (FamilyTree) fileHandler.read(filePath);
        return familyTree;

    }

    public void setWedding(long humanId1, long humanId2) {
        familyTree.setWeddingById(humanId1, humanId2);

    }

    public void setFather(long childId, long fatherId) {
        familyTree.setFatherById(childId, fatherId);
    }

    public void setMother(long childId, long motherId) {
        familyTree.setMotherById(childId, motherId);
    }
}