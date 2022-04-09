package com.fiap.challengefiap;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ChallengefiapApplication  {


    public static void main(String[] args) {
        SpringApplication.run(ChallengefiapApplication.class, args);
    }

/*

    implements CommandLineRunner

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private BebidaRepository bebidaRepository;


    @Override
    public void run(String... args) throws Exception {

        Bebida b1 = new Bebida(null, "Ipa", new BigDecimal(1), new BigDecimal(12.50));
        Bebida b2 = new Bebida(null, "Pilsen", new BigDecimal(2), new BigDecimal(10.50));
        Bebida b3 = new Bebida(null, "Bock", new BigDecimal(2), new BigDecimal(14.25));
        Bebida b4 = new Bebida(null, "Chopp", new BigDecimal(2), new BigDecimal(4.80));
        bebidaRepository.save(b1);
        bebidaRepository.save(b2);
        bebidaRepository.save(b3);
        bebidaRepository.save(b4);

        Estabelecimento et1 = new Estabelecimento(null, "Bar", LocalDate.now());
        Estabelecimento et2 = new Estabelecimento(null, "Bar Toco", LocalDate.now().plusDays(2));
        estabelecimentoRepository.save(et1);
        estabelecimentoRepository.save(et2);


        Cliente c1 = new Cliente(null, 935835634, "Ipa", "Zé");
        Cliente c2 = new Cliente(null, 93583563, "Bock", "Zézinho");
        c1.adicionaBebidas(b1);
        c1.adicionaBebidas(b2);
        c1.adicionaBebidas(b3);
        c2.adicionaBebidas(b4);

        c1.adicionaEstabelecimento(et1);
        c2.adicionaEstabelecimento(et1);

        c1.adicionaEstabelecimento(et2);


        clienteRepository.save(c1);
        clienteRepository.save(c2);

         }

 */

}


