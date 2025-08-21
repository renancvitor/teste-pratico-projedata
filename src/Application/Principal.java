package Application;

import entities.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

// 3
public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase("João"));

        //3.3
        System.out.println("\n=####= Lista de Funcionários =####=");
        funcionarios
                .forEach(System.out::println);

        // 3.4
        funcionarios
                .forEach(funcionario -> funcionario.setSalario(funcionario.getSalario()
                        .multiply(new BigDecimal("1.10"))));

        System.out.println();
        System.out.println("\n=####= Lista de Funcionários atualizada com 10% aumento salarial =####=");
        funcionarios
                .forEach(System.out::println);

        // 3.5
        System.out.println();
        System.out.print("\n=####= Lista de Funcionários agrupados por função =####=");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        // 3.6
        funcionariosPorFuncao
                .forEach((funcao, listaFuncionarios) -> {
            System.out.println("\nFunção: " + funcao);
            listaFuncionarios.forEach(funcionario -> System.out.println("  " + funcionario));
        });

        // 3.8
        System.out.println();
        System.out.print("\n=####= Lista de Funcionários aniversariantes do mês 10 e 12 =####=\n");
        funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(System.out::println);

        // 3.9
        System.out.println();
        System.out.print("\n=####= Funcionário mais velho =####=\n");
        Optional<Funcionario> maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));

        maisVelho.ifPresent(funcionario -> {
            int idade = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Mais velho:  " + funcionario.getNome() + " | Idade:  " + idade);
        });

        // 3.10
        System.out.println();
        System.out.print("\n=####= Lista de Funcionários em ordem alfabética (nome) =####=\n");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEachOrdered(System.out::println);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // 3.11
        System.out.println();
        System.out.print("\n=####= Total dos salários dos funcionários =####=\n");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total: " + numberFormat.format(totalSalarios));

        // 3.12
        System.out.println();
        System.out.print("\n=####= Salários mínimos por funcionários =####=\n");
        funcionarios
                .forEach(funcionario -> {
                    BigDecimal salariosMinimosPorPessoa = funcionario.getSalario()
                            .divide(new BigDecimal("1212"), 2, RoundingMode.HALF_UP);
                    System.out.println(funcionario.getNome() + " ganha " + salariosMinimosPorPessoa +
                            " salários mínimos");
                });
    }
}
