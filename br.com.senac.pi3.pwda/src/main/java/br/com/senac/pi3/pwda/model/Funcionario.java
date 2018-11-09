package br.com.senac.pi3.pwda.model;

public class Funcionario  extends Pessoa{
    
    //private String cpf;
    private Integer registro;
    private String departamento;
    private String cargo;

    public Funcionario(int id) {
        super(id);
    }
    
    public Funcionario() {
    }     

//    public String getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(String cpf) {
//        this.cpf = cpf;
//    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String funcao) {
        this.cargo = funcao;
    } 
}

