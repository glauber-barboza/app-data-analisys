package com.glauber.appdataanalisys.fatory;

public class VendedorFactory {
    public static class Builder {
        StringBuilder buildVendedor = new StringBuilder();
        private String id;
        private String cpf;
        private String nome;
        private String salario;
        private String delim;

        public Builder() {
            id = "001";
            cpf="1234567891234";
            nome="Diego";
            salario="50000";
            delim = "ç";
        }

        public Builder semId() {
            id = "";
            return this;
        }

        public Builder cpfInvalido() {
            cpf = "   ";
            return this;
        }

        public Builder nomeInvalido() {
            nome = "   ";
            return this;
        }

        public Builder salarioInvalido() {
            salario = "   ";
            return this;
        }

        public Builder semDelim() {
            delim = "";
            return this;
        }

        public String build() {
            buildVendedor.append(id);
            buildVendedor.append(delim);
            buildVendedor.append(cpf);
            buildVendedor.append(delim);
            buildVendedor.append(nome);
            buildVendedor.append(delim);
            buildVendedor.append(salario);
            return buildVendedor.toString();
        }
    }
}
