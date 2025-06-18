/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author Alunos
 * 
 * Baseado na tabela:
 * CREATE TABLE USERS (
 *   ID INT AUTO_INCREMENT PRIMARY KEY,
 *   FULL_NAME VARCHAR(100) NOT NULL,
 *   EMAIL VARCHAR(100) UNIQUE NOT NULL,
 *   PHONE VARCHAR(13) UNIQUE NOT NULL,
 *   PASSWORD VARCHAR(255) NOT NULL,
 *   ACTIVE BOOLEAN DEFAULT TRUE,
 *   INDEX ACTIVE_INDEX_USERS (ACTIVE)
 * );
 */
public class User implements BaseDAO {
    final String tableName = "USERS";
    private int id;
    private String nome;
    private String email;
    private String celular;
    private String senha;
    private boolean ativo;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
         if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        if (celular == null || !celular.matches("^\\+?[0-9]{10,13}$")) {
            throw new IllegalArgumentException("Celular inválido. Deve conter entre 10 e 13 dígitos.");
        }
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        if (senha.length() < 8) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }
        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula");
        }
        if (!senha.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra minúscula");
        }
        if (!senha.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um dígito");
        }
        if (!senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere especial");
        }
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String dadosSQLValues() {
        return "'"
            + this.getId() + "','"
            + this.getNome() + "', "
            + this.getEmail() + ","
            + this.getCelular()+ ","
            + this.getSenha()+ ","          
            + this.isAtivo();
    }

    @Override
    public String alteraDadosSQLValues() {
        return "FULL_NAME = '" + this.getNome() + "', "
            + "EMAIL = " + this.getEmail() + ", "
            + "PHONE = " + this.getCelular()+ ", "
            + "PASSWORD = " + this.getSenha() + ", "
            + "ACTIVE = " + this.isAtivo();
    }

    @Override
    public String termoSQLWhereById() {
        return "ID = " + this.getId();
    }

    @Override
    public String consultaSQLValues() {
        return "FULL_NAME, EMAIL, PHONE, PASSWORD, ACTIVE";
    }

    @Override
    public void importaSQLValues(List<String> dados) {
        if (dados.size() != 5) {
            throw new IllegalArgumentException("Número de dados inválido. Esperado 3 dados.");
        }
        this.setNome(dados.get(0));
        this.setEmail(dados.get(1));
        this.setCelular(dados.get(2));
        this.setSenha(dados.get(3));
        this.setAtivo(dados.get(4).toUpperCase().equals("TRUE") || dados.get(4).equals("1"));
    }
}