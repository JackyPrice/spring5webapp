package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.entity.Author;
import guru.springframework.spring5webapp.entity.Book;
import guru.springframework.spring5webapp.entity.Publisher;
import guru.springframework.spring5webapp.model.Address;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        Publisher aw = new Publisher();
        aw.setName("Addison-Wesley");
        aw.setAddressLine1("75 Arlington Street Suite");
        aw.setCity("Boston");
        aw.setState("MA");
        aw.setZip("02116");

        publisherRepository.save(aw);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(aw);
        aw.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(aw);

        Author erich = new Author("Erich", "Gamma");
        Book dp = new Book("Design Patterns: Elements of Reusuable Object-Oriented Software", "12345678765432");
        erich.getBooks().add(dp);
        dp.setPublisher(aw);
        aw.getBooks().add(dp);

        authorRepository.save(erich);
        bookRepository.save(dp);
        publisherRepository.save(aw);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");

        Publisher wrox = new Publisher();
        wrox.setName("Wrox");
        wrox.setAddressLine1("111 River Street");
        wrox.setCity("Hoboken");
        wrox.setState("NJ");
        wrox.setZip("07030");

        publisherRepository.save(wrox);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(wrox);
        wrox.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(wrox);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher " + aw.getName() + "Number of Books " + aw.getBooks().size());
    }
}
