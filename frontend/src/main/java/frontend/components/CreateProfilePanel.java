package frontend.components;

import frontend.exception.ResponseException;
import frontend.schema.*;
import frontend.util.AutoCompletion;
import frontend.util.FileChooserDemo;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;


import frontend.util.SwingCalendar.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class CreateProfilePanel extends JPanel implements InitializablePanel {

    private final WebClient webClient;
	private final UserSchema userSchema;
    private final StudentProfileSchema studentProfile;
    private final TimetableSchema timetable;
	private final Logger logger;
    private final String[] programs = {"Accounting Specialist (Offered Jointly By The Faculty Of Arts And Science And Joseph L. Rotman School Of Management) ASSPE1704X", "Accounting Specialist ASSPE2676", "Accounting Specialist ASSPE2676X", "Accounting Specialist: Financial Reporting and Control (BCom) ASSPE2672", "Actuarial Science Major ASMAJ0608", "Actuarial Science Specialist ASSPE0608", "African Studies Major ASMAJ1707", "African Studies Minor ASMIN1707", "African Studies Specialist ASSPE1707", "Al and Malka Green Yiddish Program Minor ASMIN1163", "American Studies Major ASMAJ0135", "American Studies Minor ASMIN0135", "Animal Physiology Major ASMAJ1538", "Anthropology Major (Evolutionary) ASMAJ1510", "Anthropology Major (General) ASMAJ1775", "Anthropology Major (Society, Culture, and Language) ASMAJ2112", "Anthropology Minor (General) ASMIN1775", "Anthropology Specialist (Society, Culture, and Language) ASSPE2112", "Applied Mathematics Specialist ASSPE2053", "Applied Statistics Specialist ASSPE1540", "Archaeology Major ASMAJ0155", "Archaeology Minor ASMIN0155", "Archaeology Specialist ASSPE0155", "Art History Major ASMAJ0908", "Art History Minor ASMIN0908", "Art History Specialist ASSPE0908", "Asian Canadian Studies Minor ASMIN2728", "Asian Geographies (offered jointly with the National University of Singapore) Minor ASMIN2727", "Asian Literatures and Cultures Minor ASMIN2726", "Astronomy & Astrophysics Major ASMAJ1423", "Astronomy & Astrophysics Major ASMAJ1423", "Astronomy & Astrophysics Minor ASMIN1423", "Astronomy & Astrophysics Minor ASMIN1423", "Astronomy & Physics Specialist ASSPE0271", "Astronomy & Physics Specialist ASSPE0271", "Biochemistry Major ASMAJ1762", "Biochemistry Specialist ASSPE1762", "Biodiversity and Conservation Biology Major ASMAJ0110", "Bioethics Major ASMAJ1001", "Bioethics Minor ASMIN1001", "Bioethics Specialist ASSPE1001", "Bioinformatics and Computational Biology Specialist ASSPE1868", "Biological Chemistry Specialist ASSPE1995", "Biological Physics: Advanced Physics Specialist ASSPE2739", "Biological Physics: Biochemistry Specialist ASSPE2737", "Biological Physics: Immunology Specialist ASSPE2740", "Biological Physics: Physiology Specialist ASSPE2738", "Biology Major ASMAJ2364", "Biology Minor (Jointly Offered With National University Of Singapore) ASMIN2730", "Biology Minor ASMIN2364", "Biology Specialist ASSPE2364", "Biomedical Toxicology Major ASMAJ2573", "Biomedical Toxicology Specialist ASSPE2573", "Book and Media Studies Major ASMAJ1300", "Book and Media Studies Minor AEMIN1300", "Book and Media Studies Minor ASMIN1300", "Buddhism, Psychology and Mental Health Minor ASMIN1017", "Buddhist Studies Major ASMAJ1525", "Buddhist Studies Specialist ASSPE1525", "Business Fundamentals Certificate ASCER2400", "Business German Minor ASMIN2453", "Canadian Studies Major ASMAJ0728", "Canadian Studies Minor ASMIN0728", "Canadian Studies Specialist ASSPE0728", "Caribbean Studies Major ASMAJ1545", "Caribbean Studies Minor ASMIN1545", "Caribbean Studies Specialist ASSPE1545", "Cell & Molecular Biology Major ASMAJ1003", "Cell & Molecular Biology Major: Focus In Molecular Networks of the Cell ASMAJ1003A", "Cell & Molecular Biology Major: Focus In Planet Genomics & Biotechnology ASMAJ1003B", "Cell & Molecular Biology Specialist ASSPE1003", "Cell & Molecular Biology Specialist: Focus In Molecular Networks of the Cell ASSPE1003A", "Cell & Molecular Biology Specialist: Focus In Plant Genomics & Biotechnology ASSPE1003B", "Celtic Studies Major ASMAJ1682", "Celtic Studies Minor ASMIN1682", "Celtic Studies Specialist ASSPE1682", "Certificate in Black Canadian Studies ASCER0828", "Certificate in Global German Studies ASCER1401", "Certificate in International Affairs ASCER1469", "Chemical Physics Specialist ASSPE0600", "Chemistry Major ASMAJ1376", "Chemistry Minor ASMIN1376", "Chemistry Specialist ASSPE1376", "Christianity And Culture Major ASMAJ0463", "Christianity And Culture Minor ASMIN0463", "Cinema Studies Major (Arts) ASMAJ0797", "Cinema Studies Minor (Arts) ASMIN0797", "Cinema Studies Specialist (Arts) ASSPE0797", "Classical Civilization Major ASMAJ0382", "Classical Civilization Minor ASMIN0382", "Classics (Greek and Latin) Major ASMAJ0962", "Cognitive Science (Arts Program) Major ASMAJ1445", "Cognitive Science (Science Program) Major ASMAJ1446", "Cognitive Science Major - Arts: Language and Cognition Stream ASMAJ1445B", "Cognitive Science Major - Arts: Perception and Attention Stream ASMAJ1445A", "Cognitive Science Major - Arts: Thinking and Reasoning Stream ASMAJ1445C", "Cognitive Science Major - Science: Cognition and the Brain Stream ASMAJ1446B", "Cognitive Science Major - Science: Computational Cognition Stream ASMAJ1446A", "Commerce & Finance Specialist (Offered Jointly By The Faculty Of Arts And Science And The Joseph L. Rotman School Of Management) ASSPE2273X", "Commerce Major ASMAJ1111", "Comp Sci: Information Systems Specialist ASSPE1037X", "Comp Sci: Software Engineering Specialist ASSPE1039X", "Computer Science Major ASMAJ1688X", "Computer Science Major ASMAJ1689", "Computer Science Major ASMAJ1689X", "Computer Science Minor ASMIN1689", "Computer Science Specialist ASSPE1689", "Computer Science Specialist ASSPE1689X", "Contemporary Asian Studies Major ASMAJ0235", "Contemporary Asian Studies Minor ASMIN0235", "Creative Expression and Society Minor ASMIN2741", "Criminology and Sociolegal Studies - Major ASMAJ0826", "Criminology and Sociolegal Studies - Specialist ASSPE0826", "Critical Studies in Equity & Solidarity Major ASMAJ1141", "Critical Studies in Equity & Solidarity Minor ASMIN1141", "Data Science Specialist ASSPE1687", "Developmental Biology Specialist ASSPE1305", "Diaspora & Transnational Studies Major ASMAJ1407", "Diaspora & Transnational Studies Minor ASMIN1407", "Digital Humanities Minor ASMIN1337", "Drama Major ASMAJ2148", "Drama Minor ASMIN2148", "Drama Specialist ASSPE2148", "Earth and Environmental Systems Major ASMAJ2745", "East Asian Studies Major ASMAJ1058", "East Asian Studies Minor ASMIN1058", "East Asian Studies Specialist ASSPE1058", "Ecology & Evolutionary Biology Major ASMAJ1006", "Ecology and Evolutionary Biology Specialist ASSPE1006", "Economics & Mathematics Specialist ASSPE2599", "Economics Major ASMAJ1478", "Economics Minor ASMIN1478", "Economics Specialist ASSPE1478", "Education And Society Minor (Ctep) ASMIN1028X", "Education and Society Minor ASMIN1029", "English Major ASMAJ1645", "English Minor ASMIN1645", "English Specialist ASSPE1645", "Environment & Behaviour Minor ASMIN1551", "Environment & Energy Minor ASMIN1552", "Environment & Health Major ASMAJ0365", "Environment & Health Specialist ASSPE0365", "Environment & Toxicology Specialist ASSPE0605", "Environmental Anthropology Minor ASMIN1291", "Environmental Biology Major ASMAJ1390", "Environmental Biology Minor ASMIN1390", "Environmental Chemistry Major ASMAJ2543", "Environmental Chemistry Minor ASMIN2543", "Environmental Chemistry Specialist ASSPE2543", "Environmental Economics Minor AEMIN1438", "Environmental Economics Minor ASMIN1438", "Environmental Ethics Major ASMAJ1107", "Environmental Ethics Minor ASMIN1107", "Environmental Geography Major ASMAJ1252", "Environmental Geography Minor AEMIN1252", "Environmental Geography Minor ASMIN1252", "Environmental Geography Specialist ASSPE1252", "Environmental Geosciences Minor ASMIN1253", "Environmental Geosciences Specialist ASSPE1253", "Environmental Science Major ASMAJ1076", "Environmental Science Minor ASMIN1555", "Environmental Studies Major ASMAJ1254", "Environmental Studies Minor ASMIN1254", "Equity Studies Major ASMAJ1140", "Equity Studies Minor ASMIN1140", "Estonian Studies Minor ASMIN1756", "Ethics, Society, and Law Major ASMAJ1618", "European Studies Major ASMAJ1625", "European Union Studies Minor ASMIN1011", "Finance & Economics Specialist (Offered Jointly By The Faculty Of Arts And Science And Joseph L. Rotman School Of Management) ASSPE2038X", "Finance and Economics Specialist (BCom) ASSPE2038", "Financial Economics Specialist ASSPE2722", "Financial Reporting And Control, Offered Jointly By The Faculty Of Arts And Science And Joseph L Rotman School Of Management Specialist ASSPE2672X", "Finnish Studies Major ASMAJ1089", "Finnish Studies Minor ASMIN1089", "Focus In Artificial Intelligence (Specialist) ASFOC1689B", "Focus In Astronomy And Astrophysics ASFOC1540J", "Focus In Astronomy And Astrophysics ASFOC1540J", "Focus In Astronomy And Astrophysics ASFOC2270J", "Focus In Astronomy And Astrophysics ASFOC2270J", "Focus In Biochemistry ASFOC1540O", "Focus In Biochemistry ASFOC2270O", "Focus In Cognitive Psychology ASFOC1540G", "Focus In Cognitive Psychology ASFOC2270G", "Focus In Computational Linguistics And Natural Language Processing (Specialist) ASFOC1689C", "Focus In Computer Systems (Specialist) ASFOC1689F", "Focus In Computer Vision (Specialist) ASFOC1689D", "Focus In Ecology ASFOC1540L", "Focus In Ecology ASFOC2270L", "Focus In Economics ASFOC1540N", "Focus In Economics ASFOC2270N", "Focus In Evolutionary Biology ASFOC1540M", "Focus In Evolutionary Biology ASFOC2270M", "Focus In Game Design (Specialist) ASFOC1689G", "Focus In Genes, Genetics And Biotechnology ASFOC1540D", "Focus In Genes, Genetics And Biotechnology ASFOC2270D", "Focus In Health And Disease ASFOC1540C", "Focus In Health And Disease ASFOC2270C", "Focus In Health Studies ASFOC1540A", "Focus In Health Studies ASFOC2270A", "Focus In Human-computer Interaction ASFOC1689H", "Focus In Neuroscience ASFOC1540E", "Focus In Neuroscience ASFOC2270E", "Focus In Physics ASFOC1540P", "Focus In Physics ASFOC2270P", "Focus In Psycholinguistics ASFOC1540I", "Focus In Psycholinguistics ASFOC2270I", "Focus In Scientific Computing (Specialist) ASFOC1689A", "Focus In Social Psychology ASFOC1540F", "Focus In Social Psychology ASFOC2270F", "Focus In Sociolinguistics ASFOC1540H", "Focus In Sociolinguistics ASFOC2270H", "Focus In Sociology ASFOC1540K", "Focus In Sociology ASFOC2270K", "Focus In Theory Computation ASFOC1689I", "Focus In Urban Geography ASFOC1667M", "Focus In Urban Geography ASFOC1667S", "Focus In Web And Internet Technologies ASFOC1689J", "Focus in Artificial Intelligence (Major) ASFOC1689K", "Focus in Canadian & Foreign Security (Major) ASFOC1469A", "Focus in Canadian & Foreign Security (Specialist) ASFOC1469B", "Focus in Computational Linguistics and Natural Language Processing (Major) ASFOC1689M", "Focus in Computer Vision (Major) ASFOC1689L", "Focus in Data Analytics (Major) ASFOC1478B", "Focus in Data Analytics (Specialist) ASFOC1478A", "Focus in Data Science ASFOC2038A", "Focus in Data Science ASFOC2431G", "Focus in Data Science ASFOC2676A", "Focus in Finance ASFOC2431B", "Focus in Financial Statement Analysis ASFOC2431E", "Focus in Game Design (Major) ASFOC1689N", "Focus in Global Health ASFOC1540B", "Focus in Global Health ASFOC2270B", "Focus in Green Chemistry - Biological Chemistry ASFOC1995A", "Focus in Green Chemistry - Chemical Physics ASFOC0600A", "Focus in Green Chemistry - Chemistry ASFOC1376A", "Focus in Green Chemistry - Chemistry ASFOC1376B", "Focus in Green Chemistry - Environmental Chemistry ASFOC2543A", "Focus in Green Chemistry - Environmental Chemistry ASFOC2543B", "Focus in Green Chemistry - Materials Science ASFOC2424A", "Focus in Green Chemistry - Pharmaceutical Chemistry ASFOC1211A", "Focus in Green Chemistry - Synthetic and Catalytic Chemistry ASFOC1377A", "Focus in International Business ASFOC2431F", "Focus in International Economy (Specialist) ASFOC1469C", "Focus in International Law & Human Rights (Major) ASFOC1469D", "Focus in International Law & Human Rights (Specialist) ASFOC1469E", "Focus in Law & History (Major) ASFOC0652B", "Focus in Law & History (Specialist) ASFOC0652A", "Focus in Leadership in Organizations ASFOC2431C", "Focus in Managing Diverse Economies - Accounting ASFOC2676B", "Focus in Managing Diverse Economies - Finance ASFOC2038B", "Focus in Managing Diverse Economies - Management ASFOC2431H", "Focus in Marketing ASFOC2431A", "Focus in Medical Anthropology (Major: Society, Culture & Language) ASFOC2112A", "Focus in Medical Anthropology (Major:General) ASFOC1775A", "Focus in Medical Anthropology (Specialist: Society, Culture and Language) ASFOC2112B", "Focus in Pharmacology and Biomedical Toxicology ASFOC1540Q", "Focus in Pharmacology and Biomedical Toxicology ASFOC2270Q", "Focus in Planning ASFOC1667A", "Focus in Planning ASFOC1667B", "Focus in Practical Chemistry - Biological Chemistry ASFOC1995B", "Focus in Practical Chemistry - Chemical Physics ASFOC0600B", "Focus in Practical Chemistry - Chemistry (Major) ASFOC1376C", "Focus in Practical Chemistry - Chemistry (Specialist) ASFOC1376D", "Focus in Practical Chemistry - Environmental Chemistry (Major) ASFOC2543C", "Focus in Practical Chemistry - Environmental Chemistry (Specialist) ASFOC2543D", "Focus in Practical Chemistry - Materials Science ASFOC2424B", "Focus in Practical Chemistry - Pharmaceutical Chemistry ASFOC1211B", "Focus in Practical Chemistry - Synthetic and Catalytic Chemistry ASFOC1377B", "Focus in Scientific Computing (Major) ASFOC1689O", "Focus in Strategy and Innovation ASFOC2431D", "Focus in Transnational Exchange (Major) ASFOC1469F", "Focus in Transnational Exchange (Specialist) ASFOC1469G", "Forest Biomaterials Science Major ASMAJ1098", "Forest Biomaterials Science Minor ASMIN1098", "Forest Conservation Major ASMAJ1100", "Forest Conservation Minor ASMIN1100", "Forest Conservation Science Major ASMAJ1102", "Forest Conservation Science Minor ASMIN1102", "Forest Conservation Science Specialist ASSPE1102", "Forest Conservation Specialist ASSPE1100", "French Language Learning Major ASMAJ0120", "French Language Minor ASMIN0120", "French Language and French Linguistics Major ASMAJ0525", "French Language and French Linguistics Specialist ASSPE0525", "French Language and Literature Major ASMAJ1295", "French Language and Literature Specialist ASSPE1295", "French Studies Minor ASMIN1135", "French as a Second Language Minor AEMIN0555", "Fundamental Genetics and Its Applications Major ASMAJ1050", "Fundamental Genetics and Its Applications Specialist ASSPE1050", "Genome Biology Major ASMAJ2655", "Geographic Information Systems Minor ASMIN0305", "Geology Specialist ASSPE0509", "Geophysics Specialist ASSPE1650", "Geoscience Major ASMAJ0509", "Geoscience Minor ASMIN0509", "German Studies Major ASMAJ1400", "German Studies Minor ASMIN1400", "German Studies Specialist ASSPE1400", "German Studies in English ASMIN1405", "Global Health Major ASMAJ2575", "Global Health Specialist ASSPE2575", "Greek Major ASMAJ2123", "Greek Minor ASMIN2123", "Health & Disease Major ASMAJ2013", "Health & Disease Specialist ASSPE2013", "Health Studies Major ASMAJ2085", "Health Studies Specialist ASSPE2085", "History Major ASMAJ0652", "History Minor ASMIN0652", "History Specialist ASSPE0652", "History and Philosophy of Science and Technology Major ASMAJ0667", "History and Philosophy of Science and Technology Minor ASMIN0667", "Human Biology Major ASMAJ2035", "Human Geography Major ASMAJ1667", "Human Geography Minor AEMIN1667", "Human Geography Minor ASMIN1667", "Human Geography Specialist ASSPE1667", "Hungarian Studies Major ASMAJ1124", "Hungarian Studies Minor ASMIN1124", "Immunology Major ASMAJ1002", "Immunology Minor ASMIN1002", "Immunology Specialist ASSPE1002", "Indigenous Studies Major ASMAJ0115", "Indigenous Studies Minor ASMIN0115", "Indigenous Studies Specialist ASSPE0115", "Individual Designed Arts Program: Approved By Innis College Major ASMAJ1830", "Individual Designed Arts Program: Approved By Innis College Specialist ASSPE1830", "Individual Designed Arts Program: Approved By New College Major ASMAJ1730", "Individual Designed Arts Program: Approved By New College Specialist ASSPE1730", "Individual Designed Arts Program: Approved By St. Michael's College Major ASMAJ1930", "Individual Designed Arts Program: Approved By St. Michael's College Specialist ASSPE1930", "Individual Designed Arts Program: Approved By Trinity College Major ASMAJ1630", "Individual Designed Arts Program: Approved By Trinity College Specialist ASSPE1630", "Individual Designed Arts Program: Approved By University College Major ASMAJ1530", "Individual Designed Arts Program: Approved By University College Specialist ASSPE1530", "Individual Designed Arts Program: Approved By Victoria College Major ASMAJ1430", "Individual Designed Arts Program: Approved By Victoria College Minor ASMIN1430", "Individual Designed Arts Program: Approved By Victoria College Specialist ASSPE1430", "Individual Designed Arts Program: Approved By Woodsworth College Major ASMAJ1330", "Individual Designed Arts Program: Approved By Woodsworth College Specialist ASSPE1330", "Individual Designed Science Program: Approved By Innis College Major ASMAJ1831", "Individual Designed Science Program: Approved By Innis College Specialist ASSPE1831", "Individual Designed Science Program: Approved By New College Major ASMAJ1731", "Individual Designed Science Program: Approved By New College Specialist ASSPE1931", "Individual Designed Science Program: Approved By St. Michael's College Major ASMAJ1931", "Individual Designed Science Program: Approved By St. Michael's College Specialist ASSPE1731", "Individual Designed Science Program: Approved By Trinity College Major ASMAJ1631", "Individual Designed Science Program: Approved By Trinity College Specialist ASSPE1631", "Individual Designed Science Program: Approved By University College Major ASMAJ1531", "Individual Designed Science Program: Approved By University College Specialist ASSPE1531", "Individual Designed Science Program: Approved By Victoria College Major ASMAJ1431", "Individual Designed Science Program: Approved By Victoria College Specialist ASSPE1431", "Individual Designed Science Program: Approved By Woodsworth College Major ASMAJ1331", "Individual Designed Science Program: Approved By Woodsworth College Specialist ASSPE1331", "Industrial Relations and Human Resources Major ASMAJ1536", "Industrial Relations and Human Resources Specialist ASSPE1536", "International Relations Major ASMAJ1469", "International Relations Specialist ASSPE1469", "Islamic Studies Major ASMAJ1359", "Italian Culture and Communication Studies Minor ASMIN1245", "Italian Major ASMAJ2524", "Italian Minor ASMIN2524", "Italian Specialist ASSPE2524", "Jewish Studies Major (Arts prograrm) ASMAJ0385", "Jewish Studies Minor ASMIN0385", "Jewish Studies Specialist (Arts prograrm) ASSPE0385", "Latin American Studies Major ASMAJ0552", "Latin American Studies Minor ASMIN0552", "Latin Major ASMAJ1451", "Latin Minor ASMIN1451", "Linguistics Major ASMAJ0506", "Linguistics Minor ASMIN0506", "Linguistics Specialist ASSPE0506", "Literature and Critical Theory Major in the Comparative Literature Stream ASMAJ1026", "Literature and Critical Theory Major in the Cultural Theory Stream ASMAJ1023", "Literature and Critical Theory Minor ASMIN0539", "Literature and Critical Theory Specialist in the Comparative Literature Stream ASSPE1026", "Literature and Critical Theory Specialist in the Cultural Theory Stream ASSPE1023", "Major - Economics AEMAJ1478", "Major - Mathematics AEMAJ1165", "Major in European Affairs ASMAJ1626", "Major in Public Policy ASMAJ2660", "Major: Focus In Stem Cells & Developmental Biology ASMAJ1003C", "Management Specialist (BCom) ASSPE2431", "Management Specialist (Offered Jointly By The Faculty Of Arts And Science And Joseph L. Rotman School Of Management) ASSPE2431X", "Material Culture & Semiotics Minor ASMIN2731", "Material Culture Minor ASMIN2729", "Materials Science Specialist ASSPE2424", "Mathematical Applications in Economics and Finance Specialist ASSPE1700", "Mathematics & Its Applications Specialist (Physical Science) ASSPE1758", "Mathematics & Its Applications Specialist (Probability/Statistics) ASSPE1890", "Mathematics & Its Applications Specialist (Teaching) ASSPE1580", "Mathematics Major ASMAJ1165", "Mathematics Minor ASMIN1165", "Mathematics Specialist ASSPE1165", "Mathematics and Philosophy ASSPE1361", "Mathematics and Physics Specialist ASSPE0397", "Mediaeval Studies Major ASMAJ1231", "Mediaeval Studies Minor ASMIN1231", "Mediaeval Studies Specialist ASSPE1231", "Mi Italian AEMIN2524", "Minor - Anthropology AEMIN1775", "Minor - Biology AEMIN2364", "Minor - Cinema Studies AEMIN0797", "Minor - Classical Civilization AEMIN0382", "Minor - Diaspora And Transnational Studies AEMIN1407", "Minor - East Asian Studies AEMIN1058", "Minor - Economic History AEMIN0545", "Minor - Economics AEMIN1478", "Minor - English AEMIN1645", "Minor - Fine Art (History Of Art) AEMIN0908", "Minor - French Studies AEMIN1135", "Minor - German AEMIN1400", "Minor - History AEMIN0652", "Minor - Linguistics AEMIN0506", "Minor - Mathematics AEMIN1165", "Minor - Near & Middle Eastern Civilization AEMIN1019", "Minor - Philosophy AEMIN0231", "Minor - Physics AEMIN1944", "Minor - Polish Language And Literature AEMIN2426", "Minor - Political Science AEMIN2015", "Minor - Psychology AEMIN1160", "Minor - Sociology AEMIN1013", "Minor - Spanish AEMIN0623", "Minor - Statistics AEMIN2289", "Minor Program in Christianity and Education ASMIN1014", "Minor in Advanced Manufacturing AEMINADVM", "Minor in Artificial Intelligence Engineering AEMINAIEN", "Minor in Bioengineering AEMINBIO", "Minor in Biomedical Engineering AEMINBME", "Minor in Creative Writing ASMIN1646", "Minor in Engineering Business AEMINBUS", "Minor in Environmental Engineering AEMINENV", "Minor in European Affairs ASMIN1626", "Minor in Medical Anthropology ASMIN1778", "Minor in Music Performance AEMINMUSP", "Minor in Nano Engineering AEMINNANO", "Minor in Robotics and Mechatronics AEMINRAM", "Minor in Sustainable Energy AEMINENR", "Molecular Genetics And Microbiology: Genetics Stream Specialist ASSPE1388", "Molecular Genetics And Microbiology: Microbiology Stream Specialist ASSPE1389", "Molecular Genetics and Microbiology Major ASMAJ1387", "Molecular Genetics and Microbiology Specialist ASSPE1387", "Music History & Culture Minor ASMIN0695", "Music Major ASMAJ2276", "Music Major with Ensemble Option ASMAJ2027", "Music Specialist ASSPE2276", "Music Specialist with Ensemble Option ASSPE2027", "Nanoscience Minor (offered jointly with the National University of Singapore) ASMIN2723", "Near and Middle Eastern Civilization Major (Medieval) ASMAJ2667", "Near and Middle Eastern Civilizations Major (Ancient) ASMAJ2665", "Near and Middle Eastern Civilizations Major (General) ASMAJ1019", "Near and Middle Eastern Civilizations Major (Modern) ASMAJ2669", "Near and Middle Eastern Civilizations Minor ASMIN1019", "Near and Middle Eastern Civilizations Specialist (Ancient) ASSPE2665", "Near and Middle Eastern Civilizations Specialist (General) ASSPE1019", "Near and Middle Eastern Civilizations Specialist (Medieval) ASSPE2667", "Near and Middle Eastern Civilizations Specialist (Modern) ASSPE2669", "Neuroscience Major ASMAJ1472", "Neuroscience Specialist ASSPE1472", "Nutritional Sciences Major ASMAJ1068", "Pathobiology Specialist ASSPE2025", "Peace, Conflict, and Justice Major ASMAJ1228", "Peace, Conflict, and Justice Specialist ASSPE1228", "Pharmaceutical Chemistry Specialist ASSPE1211", "Pharmacology & Toxicology Major (General) ASMAJ2675", "Pharmacology Major ASMAJ2082", "Pharmacology Specialist ASSPE2082", "Philosophy Major ASMAJ0231", "Philosophy Minor ASMIN0231", "Philosophy Specialist ASSPE0231", "Physical & Environmental Geography Minor ASMIN2030", "Physical and Environmental Geography Major ASMAJ2030", "Physical and Environmental Geography Specialist ASSPE2030", "Physics Major ASMAJ1944", "Physics Minor ASMIN1944", "Physics Specialist ASSPE1944", "Physics and Philosophy Specialist ASSPE2584", "Physiology Major ASMAJ0482", "Physiology Minor ASMIN0482", "Physiology Specialist ASSPE0482", "Planetary Science Specialist ASSPE1073", "Political Science Major ASMAJ2015", "Political Science Minor ASMIN2015", "Political Science Specialist ASSPE2015", "Portuguese Major ASMAJ0338", "Portuguese Minor ASMIN0338", "Portuguese Specialist ASSPE0338", "Practical French Minor ASMIN0556", "Psychology Major ASMAJ1160", "Psychology Minor ASMIN1160", "Psychology Research Specialist - Thesis ASSPE1958", "Psychology Specialist ASSPE1160", "Psychology of Economics and Management Certificate ASCER1160", "Public Accounting Specialist (Offered Jointly By The Faculty Of Arts And Science And Joseph L Rotman School Of Management) ASSPE2678X", "Quantitative Biology Major ASMAJ2368", "Religion Major ASMAJ0151", "Religion Minor ASMIN0151", "Religion Specialist ASSPE0151", "Religion: Christian Origins Specialist ASSPE1520", "Renaissance Studies Major ASMAJ0532", "Renaissance Studies Minor ASMIN0532", "Renaissance Studies Specialist ASSPE0532", "Russian Literature in Translation Minor ASMIN1281", "Science, Technology, and Society Minor ASMIN2743", "Semiotics and Communication Studies Minor ASMIN1939", "Sexual Diversity Studies Major ASMAJ1240", "Sexual Diversity Studies Minor ASMIN1240", "Sexual Diversity Studies Specialist ASSPE1240", "Slavic Languages and Cultures Specialist ASSPE1200", "Slavic Languages and Cultures: Czech and Slovak Major ASMAJ1200A", "Slavic Languages and Cultures: Czech and Slovak Minor ASMIN1200A", "Slavic Languages and Cultures: Polish Major ASMAJ1200B", "Slavic Languages and Cultures: Polish Minor ASMIN1200B", "Slavic Languages and Cultures: Russian Major ASMAJ1200C", "Slavic Languages and Cultures: Russian Minor ASMIN1200C", "Slavic Languages and Cultures: South Slavic Major ASMAJ1200D", "Slavic Languages and Cultures: South Slavic Minor ASMIN1200D", "Slavic Languages and Cultures: Ukrainian Major ASMAJ1200E", "Slavic Languages and Cultures: Ukrainian Minor ASMIN1200E", "Sociology Major ASMAJ1013", "Sociology Minor ASMIN1013", "Sociology Specialist ASSPE1013", "South Asian Studies Minor ASMIN1333", "Spanish Major ASMAJ0623", "Spanish Minor ASMIN0623", "Spanish Specialist ASSPE0623", "Specialist in Pharmacology and Biomedical Toxicology ASSPE2340", "Specialist in Statistical Science: Methods and Practice ASSPE2270", "Specialist in Statistical Science: Theory and Methods ASSPE2290", "Specialist: Focus In Stem Cells & Developmental Biology ASSPE1003C", "Statistics Major ASMAJ2289", "Statistics Minor ASMIN2289", "Statistics Specialist ASSPE2289", "Sustainability Pathways Certificate ASCER1500", "Synthetic & Catalytic Chemistry Specialist ASSPE1377", "TS1-Religious Education ASTS10218", "TS2-Business Studies-Accounting ASTS20001", "TS2-Business Studies-General ASTS20044", "TS2-Business Studies-Information and Communications Technology ASTS20046", "TS2-Computer Studies ASTS20422", "TS2-Dramatic Arts ASTS20135", "TS2-Economics ASTS20050", "TS2-English ASTS20319", "TS2-Family Studies ASTS20230", "TS2-French as a Second Language ASTS20325", "TS2-Geography ASTS20740", "TS2-History ASTS20750", "TS2-International Languages-German ASTS20330", "TS2-International Languages-Italian ASTS20340", "TS2-International Languages-Spanish ASTS20375", "TS2-Mathematics ASTS20652", "TS2-Philosophy ASTS20770", "TS2-Politics ASTS20760", "TS2-Science-Biology ASTS20412", "TS2-Science-Chemistry ASTS20420", "TS2-Science-General ASTS20430", "TS2-Science-Physics ASTS20470", "TS2-Social Sciences-General ASTS20165", "TS2-Visual Arts ASTS20190", "Urban Studies Major ASMAJ2207", "Urban Studies Minor ASMIN2207", "Urban Studies Specialist ASSPE2207", "Visual Studies Minor ASMIN0660", "Women and Gender Studies Major Program ASMAJ0571", "Women and Gender Studies Minor Program ASMIN0571", "Women and Gender Studies Specialist Program ASSPE0571", "Work and Organizations Major: Humanities Contexts ASMAJ1532", "Work and Organizations Major: Sciences Contexts ASMAJ1534", "Work and Organizations Major: Social Sciences Contexts ASMAJ1533", "Writing and Rhetoric Minor ASMIN2137"};
    private final Integer[] enrolmentYears = {2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};
    private final String[] colleges = {"Innis College", "New College", "St. Michael's College", "Trinity College", "University College", "Victoria College", "Woodsworth College"};
    private final String[] MBTIs = {"", "ESTJ", "ENTJ", "ESFJ", "ENFJ", "ISTJ", "ISFJ", "INTJ", "INFJ", "ESTP", "ESFP", "ENTP", "ENFP", "ISTP", "ISFP", "INTP", "INFP"};
    private final String[] Sessions = {"F", "S"};

    private final JLabel title = new JLabel("title", SwingConstants.CENTER);
    private final JComboBox<String> jcbProgramOfStudy= new JComboBox<String>(programs);
    private final JComboBox<Integer> jcbEnrolmentYears = new JComboBox<Integer>(enrolmentYears);
    private final JComboBox<String> jcbColleges  = new JComboBox<String>(colleges);
    private final JButton jbtTimetable = new JButton("Upload ICS");
    private final JFileChooser jfcTimetable = new JFileChooser();
    private final FileChooserDemo fileComponent = new FileChooserDemo();
    private File timetableFile = new File("");
    private JComboBox<String> jcbSession = new JComboBox<String>(Sessions);
    private final JComboBox<String> jcbMBTI = new JComboBox<>(MBTIs);
    private final JSlider jsTalkativeness = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
    private final JSlider jsCollaborate = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
    private final JTextField jtfIg = new JTextField();
    private final JTextField jtfFb = new JTextField();

    private final JLabel jlblEnrolmentYear = new JLabel("Enrolment Year *");
    private final JLabel jlblProgramOfStudy = new JLabel("ProgramOfStudy *");
    private final JLabel jlblColleges = new JLabel("College *");
    private final JLabel jlblTimetable = new JLabel("Upload Timetable ICS *");
    private final JLabel jlblMBTI = new JLabel("MBTI");
    private final JLabel jlblTalkativeness = new JLabel("Talkativenss");
    private final JLabel jlblCollaborate = new JLabel("Willingness to Collaborate");
    private final JLabel jlblIg = new JLabel("Instagram Username");
    private final JLabel jlblFb = new JLabel("Facebook Username");

    private final JButton jbtOk = new JButton("Create");
    private final JButton jbtCancel = new JButton("Cancel");

    private final JLabel jlblStatus = new JLabel(" ");

    @Autowired
	public CreateProfilePanel(WebClient webClient, UserSchema userSchema,
                              StudentProfileSchema studentProfile, TimetableSchema timetableSchema, Logger logger) {
		this.webClient = webClient;
		this.userSchema = userSchema;
        this.studentProfile = studentProfile;
        this.timetable = timetableSchema;
		this.logger = logger;
	}

    @Override
    public void initialize(MainPanel parent) {
        JPanel jpProgram = new JPanel(new GridLayout(1, 2));
        jpProgram.add(jlblProgramOfStudy);
        AutoCompletion.enable(jcbProgramOfStudy);
        jpProgram.add(jcbProgramOfStudy);

        JPanel jpEnrolment = new JPanel(new GridLayout(1, 2));
        jpEnrolment.add(jlblEnrolmentYear);
        jpEnrolment.add(jcbEnrolmentYears);

        JPanel jpColleges = new JPanel(new GridLayout(1, 2));
        jpColleges.add(jlblColleges);
        AutoCompletion.enable(jcbColleges);
        jpColleges.add(jcbColleges);

        JPanel jpTimetable = new JPanel(new GridLayout(1, 2));
        jpTimetable.add(jlblTimetable);
        jfcTimetable.setAcceptAllFileFilterUsed(false);
        jfcTimetable.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()){
                    return true;
                }
                return f.getName().endsWith(".ics");
            }

            @Override
            public String getDescription() {
                return "Internet Calender Scheduling File";
            }
        });
        jpTimetable.add(jbtTimetable);

        JPanel jpSession = new JPanel(new GridLayout(1, 2));
        jpSession.add(new JLabel("Current Session"));
        jpSession.add(jcbSession);

        JPanel jpIg = new JPanel(new GridLayout(1, 2));
        jpIg.add(jlblIg);
        jpIg.add(jtfIg);

        JPanel jpFb = new JPanel(new GridLayout(1, 2));
        jpFb.add(jlblFb);
        jpFb.add(jtfFb);

        JPanel jpMBTI = new JPanel(new GridLayout(1, 2));
        jpMBTI.add(jlblMBTI);
        jpMBTI.add(jcbMBTI);

        JPanel jpTalkativeness = new JPanel(new GridLayout(1, 2));
        jpTalkativeness.add(jlblTalkativeness);
        jsTalkativeness.setMajorTickSpacing(1);
        jsTalkativeness.setPaintTicks(true);
        jsTalkativeness.setSnapToTicks(true);
        JPanel jst = new JPanel(new GridLayout(1, 3));
        jst.add(new JLabel("Not talkative"));
        jst.add(jsTalkativeness);
        jst.add(new JLabel(" Very talkative"));
        jpTalkativeness.add(jst);


        JPanel jpCollaborate = new JPanel(new GridLayout(1,2));
        jpCollaborate.add(jlblCollaborate);
        jsCollaborate.setMajorTickSpacing(1);
        jsCollaborate.setPaintTicks(true);
        jsCollaborate.setSnapToTicks(true);
        JPanel jsc = new JPanel(new GridLayout(1, 3));
        jsc.add(new JLabel("Not collaborative"));
        jsc.add(jsCollaborate);
        jsc.add(new JLabel(" Very collaborative"));
        jpCollaborate.add(jsc);

        JPanel p2 = new JPanel();
        p2.add(jbtOk);
        p2.add(jbtCancel);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.CENTER);
        p5.add(jlblStatus, BorderLayout.NORTH);
        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mid = new JPanel(new GridLayout(35, 1));
        mid.add(new JPanel());
        mid.add(title);
        mid.add(new JPanel());
        mid.add(jpProgram);
        mid.add(new JPanel());
        mid.add(jpEnrolment);
        mid.add(new JPanel());
        mid.add(jpColleges);
        mid.add(new JPanel());
        mid.add(jpTimetable);
        mid.add(new JPanel());
        mid.add(jpSession);
        mid.add(new JPanel());
        mid.add(new JLabel("Socials", SwingConstants.CENTER));
        mid.add(new JPanel());
        mid.add(jpIg);
        mid.add(new JPanel());
        mid.add(jpFb);
        mid.add(new JPanel());
        mid.add(new JLabel("Habits", SwingConstants.CENTER));
        mid.add(new JPanel());
        mid.add(jpMBTI);
        mid.add(new JPanel());
        mid.add(jpTalkativeness);
        mid.add(new JPanel());
        mid.add(jpCollaborate);
        mid.add(new JPanel());
        mid.add(jlblStatus);
        mid.add(p5);

        setLayout(new GridLayout(1, 3));
        add(new JPanel());
        add(mid);
        add(new JPanel());

        jbtTimetable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = jfcTimetable.showOpenDialog(fileComponent);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    timetableFile = jfcTimetable.getSelectedFile();
                    jbtTimetable.setText(timetableFile.getName());
                } else {
                    jlblStatus.setText("File Opening Error");
                }
            }
        });

        jbtOk.addActionListener(e -> {
//                Make API request here
            if ("".equals(timetableFile.getName())){
                jlblStatus.setText("Please fill in all required fields.");
            } else {
                Map<String, Object> body = new HashMap<>();
				body.put("userId", (Long) userSchema.getId());
				body.put("program", (String) jcbProgramOfStudy.getSelectedItem());
                body.put("college", (String) jcbColleges.getSelectedItem());
                body.put("enrolmentYear", (Integer) jcbEnrolmentYears.getSelectedItem());
				Mono<StudentProfileSchema> response = webClient.post()
						.uri("/student-profile/create")
						.body(BodyInserters.fromValue(body))
						.retrieve()
						.bodyToMono(StudentProfileSchema.class)
						.doOnError(ResponseException.class, exception -> {
							if (exception.hasFieldErrors()) {
								for (Map<String, String> fieldError : exception.getErrors()) {
									logger.error(fieldError.get("message"));
									if (fieldError.get("field").equals("program")) {
//										programError.setText(fieldError.get("message"));
									} else if (fieldError.get("field").equals("password")) {
//										passwordError.setText(fieldError.get("message"));
									}
								}
							} else {
								logger.error(exception.getMessage());
								jlblStatus.setText(exception.getMessage());
							}
						})
						.onErrorComplete();

				response.subscribe(v -> {
                    studentProfile.setId(v.getId());
                    studentProfile.setProgram(v.getProgram());
                    studentProfile.setCollege(v.getCollege());
                    studentProfile.setEnrolmentYear(v.getEnrolmentYear());
                    userSchema.setStudentProfile(studentProfile);
                    parent.setUserSchema(userSchema);

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                    MultipartBodyBuilder builder = new MultipartBodyBuilder();
                    builder.part("studentProfileId", v.getId()).header("Content-Type", "application/json");
                    builder.part("session", (String) Objects.requireNonNull(jcbSession.getSelectedItem())).header("Content-Type", "application/json");
                    builder.part("file", new FileSystemResource(jfcTimetable.getSelectedFile()));
                    HttpEntity<MultiValueMap<String, HttpEntity<?>>> requestEntity = new HttpEntity<>(builder.build(), headers);

                    String serverUrl = "http://localhost:8080/api/timetable/create";

                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<TimetableSchema> res = restTemplate
                            .postForEntity(serverUrl, requestEntity, TimetableSchema.class);

                    logger.info(res.toString());
                    timetable.setId(Objects.requireNonNull(res.getBody()).getId());
                    timetable.setBlocks(res.getBody().getBlocks());
                    parent.setTimetableSchema(timetable);
                    parent.getHomePanel().initialize(parent);

                    if (jtfFb.getText().trim().length() > 0 || jtfIg.getText().trim().length() > 0){
                        Map<String, Object> socialBody = new HashMap<>();
                        socialBody.put("studentProfileId", v.getId());
                        socialBody.put("instagramId", jtfIg.getText());
                        socialBody.put("facebookId", jtfFb.getText());
                        Mono<SocialMediaProfileSchema> socialResponse = webClient.post()
                                .uri("/social-profile/create")
                                .body(BodyInserters.fromValue(socialBody))
                                .retrieve()
                                .bodyToMono(SocialMediaProfileSchema.class)
                                .doOnError(ResponseException.class, exception -> {
                                    if (exception.hasFieldErrors()) {
                                        for (Map<String, String> fieldError : exception.getErrors()) {
                                            logger.error(fieldError.get("message"));
                                            if (fieldError.get("field").equals("instagramId")) {
                                                logger.info("instagramId not found");
                                            } else if (fieldError.get("field").equals("facebookId")) {
                                                logger.info("facebookId");
                                            }
                                        }
                                    } else {
                                        logger.error(exception.getMessage());
                                        jlblStatus.setText(exception.getMessage());
                                    }
                                })
                                .onErrorComplete();
                        socialResponse.subscribe(s -> {
                            studentProfile.setSocialMediaProfile(s);
                            parent.getUserSchema().setStudentProfile(studentProfile);
                        });
                    }

                    if (!Objects.equals(jcbMBTI.getSelectedItem(), "")) {
                        Map<String, Object> habitBody = new HashMap<>();
                        habitBody.put("studentProfileId", v.getId());
//				body.put("mbti", jtfIg.getText());
                        habitBody.put("talkative", jsTalkativeness.getValue());
                        habitBody.put("collaborative", jsCollaborate.getValue());
                        logger.info(habitBody.toString());
                        Mono<HabitSchema> habitResponse = webClient.post()
                                .uri("/habit/create")
                                .body(BodyInserters.fromValue(habitBody))
                                .retrieve()
                                .bodyToMono(HabitSchema.class)
                                .doOnError(ResponseException.class, exception -> {
                                    if (exception.hasFieldErrors()) {
                                        for (Map<String, String> fieldError : exception.getErrors()) {
                                            logger.error(fieldError.get("message"));
                                            if (fieldError.get("field").equals("talkative")) {
                                                logger.info("talkative not found");
                                            } else if (fieldError.get("field").equals("collaborative")) {
                                                logger.info("collaborative not found");
                                            }
                                        }
                                    } else {
                                        logger.error(exception.getMessage());
                                        jlblStatus.setText(exception.getMessage());
                                    }
                                })
                                .onErrorComplete();
                        habitResponse.subscribe(h -> {
                            studentProfile.setHabit(h);
                            parent.getUserSchema().setStudentProfile(studentProfile);
                        });
                    }
                    else {
                        parent.setPanel("HomePanel");
                    }
                });
            }

            });
    }
}