package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 등록
            /*Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);*/

            // 조회
            /*Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());*/

            // 만약 나이가 18살 이상인 회원을 모두 검색하고 싶다면? -> JPQL 사용
            // 테이블을 대상으로 코드를 짜는 것이 아니라, 객체를 대상으로 코드를 짠다. 아래 쿼리의 경우 Member 객체를 다 가져오라는 의미
            // dialect (방언) 의 종류를 바꿔도 (Oracle, Mysql, H2 등..) JPQL을 변경하지 않아도 된다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 삭제
            /*Member findMember = em.find(Member.class, 2L);
            em.remove(findMember);*/

            // 수정
            /*Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");*/

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }
}
